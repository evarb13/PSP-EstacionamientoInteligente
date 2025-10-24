public class Coche extends Thread{
    private String nombre;
    private boolean vip;
    private Estacionamiento estacionamiento;

    public Coche(String nombre, boolean vip, Estacionamiento estacionamiento) {
        this.nombre = nombre;
        this.vip = vip;
        this.estacionamiento = estacionamiento;
    }

    boolean esVip(){
        return vip;
    }

    @Override
    public void run(){
        boolean aparcado = estacionamiento.entrar(this);
        double numero = (Math.random()* 20+10);

        try{
            if (aparcado){
                Thread.sleep((long)(numero*1000));
                System.out.println(this+" coche ha salido");
            } else {
                System.out.println(this+ " No ha logrado entar");
                estacionamiento.contadorCochesNoAparcados++;
            }

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            estacionamiento.salir(this);
        }
    }

    @Override
    public String toString() {
        return "["+vip+"] "+nombre;
    }
}
