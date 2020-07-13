import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpPacket extends Thread{
   synchronized boolean isOut(String str){
        return "exit".equals(str);

    }


    public static void main(String[] args) throws IOException {
        UdpPacket udpPacket=new UdpPacket();
//        Thread thread1=new Thread();
//        thread1.start();
//        Thread thread=new Thread();
//        thread.start();
//        Thread thread=new Thread();
//        thread.start();
//        System.out.println("请输入指令");
//        Scanner scanner = new Scanner(System.in);
//        String isExit= scanner.nextLine();
//        udpPacket.isOut(isExit);

        try {
//            DatagramSocket socket = new DatagramSocket(5000);
            DatagramSocket socket= new DatagramSocket(5000);

            System.out.println("请输入指令");
            Scanner scanner = new Scanner(System.in);
            String isExit= scanner.nextLine();
            if( udpPacket.isOut(isExit)){
                sleep(5000);
                System.out.println(isExit);
                socket.close();
                return;
            }

            byte[] bytes=new byte[1024];
            DatagramPacket packet=new DatagramPacket(bytes,0,bytes.length);
            socket.receive(packet);
            String string=new String(packet.getData(),0,packet.getLength());
            System.out.println(packet.getAddress().getHostAddress());
            System.out.println(packet.getPort());
            System.out.println(string);
            if(udpPacket.isOut(string)){
                sleep(2000);
                socket.close();
            }

        } catch (SocketException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
