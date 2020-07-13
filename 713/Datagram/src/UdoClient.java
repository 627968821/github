import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;


public class UdoClient {
    public void catchUdp() {

    }

    public static void main(String[] args) {
//        List<Integer> integerList = new LinkedList<>();
//        for (int i = 1; i <= 65535; i++) {
//            try {
//                DatagramSocket socket = new DatagramSocket(i);
//                socket.close();
//            } catch (SocketException e) {
////                e.printStackTrace();
//                integerList.add(i);
//            }
//        }
//        for (Integer integer : integerList) {
//            System.out.println(integer);
//        }
        try {
            DatagramSocket socket=new DatagramSocket(4000);
            String name="卞卞";
            byte[] bytes=name.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes,0,bytes.length,InetAddress.getByName("192.168.9.27"),5000);
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
