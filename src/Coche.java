public class Coche {
    private String nombre;
    private boolean vip;
    private Estacionamiento estacionamiento;

    boolean esVip(){
        return false;
    }

    @Override
    public String toString() {
        return "["+vip+"] "+nombre;
    }
}
