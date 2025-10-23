public class Coche extends Thread{
    private String nombre;
    private boolean vip;
    private Estacionamiento estacionamiento;

    boolean esVip(){
        return vip;
    }

    @Override
    public void run(){
        boolean aparcado = estacionamiento.entrar(this);
        double numero = (Math.random()* 4 + 2);

        try{
            if (aparcado){
                Thread.sleep((long)(numero*1000));
                estacionamiento.salir(this);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "["+vip+"] "+nombre;
    }
}
