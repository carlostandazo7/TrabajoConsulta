package ThreadModel;


public class MainThread{
    public static void main(String[] args) {
        System.out.println("Hilo principal iniciando.");

        MyThreadUses mh=new MyThreadUses("#1");

        Thread nuevoh=new Thread(mh);

        nuevoh.start();
        for (int i=0; i<50;i++){
            System.out.print(" .");
        }try{
            Thread.sleep(100);
        }catch (InterruptedException exc){
            System.out.println("Hilo principal interrumpido.");
        }
        System.out.println("Hilo principal finalizado.");
    }
}
