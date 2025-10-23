import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Estacionamiento {
    private final int capacidadMax=5;
    private final Semaphore semaphore = new Semaphore(5);
    private final ArrayList<Coche> cochesAparcados = new ArrayList<>();

    synchronized boolean entrar (Coche coche) {
        try{
            if (semaphore.tryAcquire(5, TimeUnit.SECONDS)){
                if (cochesAparcados.size() < capacidadMax) {
                    cochesAparcados.add(coche);
                    System.out.println(coche+ " Ha entrado un coche");
                    return true;
                } else {
                    if (coche.esVip()) {
                        desalojarCocheNormal(coche);
                        cochesAparcados.add(coche);
                        System.out.println(coche+ " Ha entrado un coche vip");
                        return true;
                    }
                }
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
