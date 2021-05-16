package ThreadModel;

public class MyThreadUses implements Runnable {

    String nombreHilo;
    
    MyThreadUses(String nombre){
        nombreHilo=nombre;
    }

    public void run(){
        System.out.println("Comenzando "+nombreHilo);
        try {
            for (int contar=0; contar<10; contar++){
                Thread.sleep(400);
                System.out.println("En "+nombreHilo+", el recuento "+contar);
            }
        }catch (InterruptedException exc){
            System.out.println(nombreHilo + "Interrumpido.");
        }
        System.out.println("Terminando "+nombreHilo);
    }
}