import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Estacionamiento {
    private final int capacidadMax=5;
    private final Semaphore semaphore = new Semaphore(5);
    private final ArrayList<Coche> cochesAparcados = new ArrayList<>();

    boolean entrar (Coche coche){
        return false;
    }

    void salir (Coche coche){

    }

    void desalojarCocheNormal(Coche cocheVip){

    }
}
