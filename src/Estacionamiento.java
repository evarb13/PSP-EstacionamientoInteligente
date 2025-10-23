import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Estacionamiento {
    private final int capacidadMax=5;
    private final Semaphore semaphore = new Semaphore(5);
    private final ArrayList<Coche> cochesAparcados = new ArrayList<>();

    synchronized boolean entrar (Coche coche) {
        if (cochesAparcados.size() < capacidadMax) {
            cochesAparcados.add(coche);
            return true;
        } else {
            try {
                if (semaphore.tryAcquire(5, TimeUnit.SECONDS)) {
                    if (coche.esVip()) {
                        desalojarCocheNormal(coche);
                        cochesAparcados.add(coche);
                        return true;
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false;
    }

    synchronized void salir (Coche coche){
        cochesAparcados.remove(coche);
        semaphore.release();
    }

    void desalojarCocheNormal (Coche cocheVip) {
        if (cochesAparcados.size() < capacidadMax) {
            for (int i = 0; i < cochesAparcados.size(); i++) {
                if (!cochesAparcados.get(i).esVip()) {
                    salir(cochesAparcados.get(i));
                }
            }
        }
    }
}
