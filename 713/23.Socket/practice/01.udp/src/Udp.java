import java.io.IOException;
import java.net.*;
import java.util.Scanner;

//### 编写一个Udp服务器，服务器接收Udp客户端发送的消息，另外服务器也会接收键盘输入，当键盘输入quit，则服务器程序退出
//
//        ### 编写一个Udp客户端，从键盘接收目标ip地址，端口号和发送的消息，将消息发送到指定位置
//
//        ```
//        例如，输入
//        192.168.12.1 5000 你好
//        如果输入不满足上述条件，则提示输入不合法，然后继续接收输入，直到输入quit，程序退出
//        ```
public class Udp {
    void udp() {
        {
            try {
                DatagramSocket socket = new DatagramSocket();
                System.out.println("请输入ip地址，端口号，信息");
                Scanner scanner = new Scanner(System.in);
                String ipStr = scanner.nextLine();
                if (ipStr.equals("quit")) {
                    System.out.println("退出");
                    return;
                }
                String[] strings = ipStr.split(" ");
                int port= 0;
                try {
                    port = Integer.parseInt(strings[1]);
                } catch (NumberFormatException e) {
                    System.out.println("端口输入有误");
                    udp();
                }
                byte[] bytes = strings[2].getBytes();
                DatagramPacket packet = new DatagramPacket(bytes, 0, bytes.length, InetAddress.getByName(strings[0]), port);
                socket.send(packet);
                socket.close();
                System.out.println("发送成功");
            } catch (SocketException | UnknownHostException e ) {
                System.out.println("地址有误");
                udp();
            } catch (IOException e) {
                e.printStackTrace();
                udp();
            }
        }
    }
    public static void main(String[] args) {
        Udp udp = new Udp();
        udp.udp();

    }
}