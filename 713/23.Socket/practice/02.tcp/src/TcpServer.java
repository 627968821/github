import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    //    ### 加解密的服务器和客户端
//
//    先发一个byte或一行，告诉服务器端是加密还是解密，然后再发一个byte或一行，告诉服务器端的总共的文本数量。
//    然后将文本发送给服务器端。服务器端返回加密或解密后的文本。
//
//    服务器先发送文本长度，然后在发送文本内容，将加密或解密后的内容返回给客户端。
    public static void main(String[] args) {
        String PATH = "/Users/edz/project/JAVA/713/23.Socket/practice/02.tcp/alice.code";
        File file = new File(PATH);
        try {
            Socket socket = new Socket("192.0.0.1", 5000);
            OutputStream outputStream = socket.getOutputStream();
            System.out.println("启动客户端");
            System.out.println("建立连接");
            String isParse = "加密";
            byte[] bytes = isParse.getBytes();
            outputStream.write(bytes);
            int len=(int)file.length();
            String strLen=Integer.toString(len);
            char[] chars = strLen.toCharArray();
            for (char aChar : chars) {
                outputStream.write(aChar);
            }
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            String strLine=reader.readLine();
            while (strLine.isEmpty()){
                char[] charLine= strLine.toCharArray();
                for (char c : charLine) {
                    outputStream.write(c);
                }
                strLine=reader.readLine();
            }

//        BufferedWriter op=new BufferedWriter(new FileWriter(PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
