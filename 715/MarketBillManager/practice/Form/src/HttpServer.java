import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.Provider;

public class HttpServer {

    static User user = new User("卞卞", "hanhan");

    public static void main(String[] args) throws IOException {
        // TODO 读文件
      new HttpServer().setUser();
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {
            Socket clientSocket = serverSocket.accept();
            SocketHandler socketHandler = new SocketHandler(clientSocket);
            socketHandler.start();
        }

        //  写返回值
    }

    public void setUser() throws IOException {
        String line = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\86187\\github\\715\\MarketBillManager\\practice\\Form\\resources\\welcome.html"));
               BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter("C:\\Users\\86187\\github\\715\\MarketBillManager\\practice\\Form\\resources\\welcome.html"));
                line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains("${name}")) {
                    line.replace("${name}", user.getName());

                }
                if (line.contains("${pwd}")) {
                    line.replace("${pwd}", user.getPwd());
                }
                bufferedWriter.write(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
