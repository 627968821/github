import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpServer {
    public static void main(String[] args) throws IOException, SocketException {
        while (true){
        DatagramSocket socket = new DatagramSocket(5000);
        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, 0,buf.length);
        socket.receive(packet);
        System.out.println(packet.getAddress().getHostAddress());
        System.out.println(new String(packet.getData(), 0, packet.getLength()));
    }}
}
