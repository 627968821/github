import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpPacket {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(5000);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
