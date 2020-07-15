import java.io.*;
import java.net.Socket;
import java.util.StringTokenizer;

public class SocketHandler extends Thread {
    Socket clientSocket;

    public SocketHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = clientSocket.getInputStream();
            System.out.println(clientSocket.getInetAddress().getHostAddress());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            if (line == null) {
                System.out.println("解析失败");
                return;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(line);
            MbmRequest mbmRequest = new MbmRequest();
            mbmRequest.setMethod(stringTokenizer.nextToken());
            mbmRequest.setPath(stringTokenizer.nextToken());
            while (line != null && line.length() != 0) {
                if (line.startsWith("Host:")) {
                    mbmRequest.setHost(line.replace("Host:", ""));
                }
                if (line.startsWith("Content-Length:")) {
                    mbmRequest.setContentLength(Integer.parseInt(line.replace("Content-Length: ", "")));
                }
                System.out.println(line);
                line = bufferedReader.readLine();
            }
            System.out.println(mbmRequest);
            if (mbmRequest.getContentLength() > 0) {
                char[] chars = new char[mbmRequest.getContentLength()];
                bufferedReader.read(chars);
            }
            String resourcePath = mbmRequest.getPath();
            if (resourcePath.startsWith("/server")) {
                process(mbmRequest);
            } else {
                responseResource(resourcePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void responseResource(String path) {//响应资源
        InputStream inputStream = HttpServer.class.getClassLoader().getResourceAsStream(path);;
        if (path.startsWith("/")) {
            path = path.substring(1);
        }
        if (path.equals("login.html") || path.equals("")) {
            inputStream = HttpServer.class.getClassLoader().getResourceAsStream(path);
        } else if (path.equals("welcome.html")) {
            inputStream = HttpServer.class.getClassLoader().getResourceAsStream(path);
        } else {
            inputStream = HttpServer.class.getClassLoader().getResourceAsStream("404.html");
        }
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write("HTTP/1.1 200 OK\n".getBytes());
            outputStream.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
            outputStream.write(("Content-Length: " + inputStream.available()).getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(MbmRequest mbmRequest) {//处理登录
        switch (mbmRequest.getPath()) {
            case "/server/login":
                responseRedirect(mbmRequest, "/welcome.html");
                break;
        }
    }

    public void responseRedirect(MbmRequest mbmRequest, String path) {//响应重定项
        try {
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write("HTTP/1.1 302 Found".getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(("Location: " + " http://" + mbmRequest.getHost() + path).getBytes());
            outputStream.write("\r\n".getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
