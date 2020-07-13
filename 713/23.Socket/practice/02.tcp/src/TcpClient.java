import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpClient {
    public static void main(String[] args) {
        try {
            ServerSocket socket=new ServerSocket(5000);
            System.out.println("服务器启动");
            Socket socket1= socket.accept();
            System.out.println(socket1.getInetAddress().getHostAddress() + "connected...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
