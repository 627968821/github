package club.banyuan;

public class ReverseHelloMultithreaded extends Thread{
    public static void doReverseHello() {
        for (int i = 50; i >=1 ; i--) {
            Thread thread = new Thread();
            thread.start();
            System.out.println("Hello from Thread "+i+" !");
        }
    }

//    @Override
//    public void run() {
//        Thread thread = new Thread();
//    }

    public static void main(String[] args) {
        doReverseHello();
    }
}
