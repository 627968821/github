import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Provider;

public class HttpServer {

  static User user=new User("卞卞","hanhan");
  public static void main(String[] args) throws IOException {
    // TODO 读文件
    ServerSocket serverSocket = new ServerSocket(5000);
    while (true){
      Socket clientSocket=serverSocket.accept();
      SocketHandler socketHandler=new SocketHandler(clientSocket);
      socketHandler.start();
    }
//    String line = "";
//    if (line.contains("${name}")) {
//      line.replace("${name}", user.getName());
//    }
//
//    if (line.contains("${pwd}")) {
//      line.replace("${pwd}", user.getPwd());
//    }

    // 写返回值
  }
}
