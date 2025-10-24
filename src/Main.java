import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Estacionamiento estacionamiento = new Estacionamiento();
        ArrayList<Coche> coches = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Coche coche = new Coche("Coche "+i, false, estacionamiento);
            coches.add(coche);
        }
        for (int i = 11; i <= 15; i++) {
            Coche coche = new Coche("Coche "+i, true, estacionamiento);
            coches.add(coche);
        }
        try{
            for (Coche coche:coches){
                coche.start();
            }


            //HOLA
            for (Coche coche:coches){
                coche.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        estacionamiento.contadores();
    }
}