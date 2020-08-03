package club.banyuan.mbm.server;

import club.banyuan.mbm.entity.Bill;
import club.banyuan.mbm.entity.Provider;
import club.banyuan.mbm.entity.User;
import club.banyuan.mbm.exception.BadRequestException;
import club.banyuan.mbm.exception.FormPostException;
import club.banyuan.mbm.exception.ValidationException;
import club.banyuan.mbm.service.*;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class SocketHandlerSql extends Thread {

    private Socket clientSocket;
    private UserServiceSql userServiceSql = new UserServiceSql();
    private ProviderServiceSql providerServiceSql = new ProviderServiceSql();
    private BillServiceSql billServiceSql = new BillServiceSql();
    private Logins logins=new Logins();
//    private static User user = new User();//当前登陆用户

    public SocketHandlerSql(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        MbmRequestSql mbmRequestSql = new MbmRequestSql();
        try {
            // 开启浏览器的流，获取浏览器发送的数据
            InputStream inputStream = clientSocket.getInputStream();
            System.out.println(clientSocket.getInetAddress().getHostAddress());
            // 为了有更好的处理方法，将字节流转换为字符流
            // InputStreamReader 字节流到字符流的转换
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = bufferedReader.readLine();
            if (line == null) {
                // 建立tcp连接之后，通常不会读取到null，除非连接关闭
                System.err.println("解析失败");
                return;
            }
            // GET / HTTP1.1
            StringTokenizer tokenizer = new StringTokenizer(line);
            mbmRequestSql.setMethod(tokenizer.nextToken());
            mbmRequestSql.setPath(URLDecoder.decode(tokenizer.nextToken(), "utf-8"));

            // 循环读取请求头的信息，按照一行一行的方式读取，读取到空行则退出循环
            while (line != null && line.length() != 0) {
                if (line.startsWith("Host:")) {
                    mbmRequestSql.setHost(line.replace("Host: ", ""));
                }
                if (line.startsWith("Content-Length:")) {
                    mbmRequestSql.setContentLength(Integer.parseInt(line.replace("Content-Length: ", "")));
                }
                System.out.println(line);
                line = bufferedReader.readLine();
            }

            System.out.println(mbmRequestSql);
            if (mbmRequestSql.getContentLength() > 0) {
                char[] chars = new char[mbmRequestSql.getContentLength()];
                bufferedReader.read(chars);

                mbmRequestSql.setPayload(URLDecoder.decode(new String(chars), "utf-8"));
                // 断言
                // assert read == mbmRequest.getContentLength();
            }

            String resourcePath = mbmRequestSql.getPath();

            if (resourcePath.startsWith("/server")) {
                // 服务接口，需要对应每个路径一个处理代码块
                process(mbmRequestSql);
            } else {
                // 资源文件的路径，需要读取对应的文件内容返回
                responseResource(mbmRequestSql);
            }
        } catch (FormPostException | ValidationException | BadRequestException e) {
            try {
                responseRedirect(mbmRequestSql, "/form_post_fail.html?msg=" + e.getMessage());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 处理请求数据
     *
     * @param mbmRequest 请求数据的封装对象
     */
    private void process(MbmRequestSql mbmRequest) throws IOException {
//        if (mbmRequest.getPath().contains("#")) {
//            responseResource(mbmRequest);
//        }
        switch (mbmRequest.getPath()) {

            case "/server/user/login": {
                Map<String, String> formData = mbmRequest.getFormData();
                if (userServiceSql.login(formData.get("name"), formData.get("pwd"))==null) {
                    // 登录失败， 跳转到login.html
                    throw new FormPostException("密码错误!");
                    // responseRedirect(mbmRequest, "/login.html");
                } else {
                    // 登录成功，跳转到 bill_list.html
                    String ip = clientSocket.getInetAddress().getHostAddress();
                    User user = userServiceSql.getUserByName(formData.get("name"));
                    user.setIp(ip);
                    logins.addUser(user);
//                    user = userServiceSql.getUserByName(formData.get("name"));//获取当前登录的user对象
                    responseRedirect(mbmRequest, "/bill_list.html");
                }
            }
            break;
            case "/server/user/list": {
                List<User> userList;
                // 如果payload不为null，表示需要根据用户名进行检索
                String payload = mbmRequest.getPayload();
                if (payload == null) {
                    userList = userServiceSql.getUserList();

                } else {
                    // 将待检索的用户名转换为user对象
                    User user = JSONObject.parseObject(payload, User.class);
                    userList = userServiceSql.getUserList(user);
                }
                responseJson(userList);
            }
            break;
            case "/server/user/modify": {
                // map返回的数据key 和user的属性名基本一致，因此可以将map序列化成json字符串，然后把这个字符串再反序列化成User
                Map<String, String> formData = mbmRequest.getFormData();
                String data = JSONObject.toJSONString(formData);
                User user = null;
                try {
                    user = JSONObject.parseObject(data, User.class);
                } catch (Exception e) {
                    throw new BadRequestException("输入不能为空");
                }
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                User userByIp = logins.getUserByIp(hostAddress);
                if(userByIp.getUserType()!=1){
                    throw new ValidationException("权限不足");
                }
                if (user.getId() == 0) {
                    userServiceSql.addUser(user);
                } else {
                    System.out.println(mbmRequest.getPayload());
                    userServiceSql.updateUser(user);
                }
                responseRedirect(mbmRequest, "/user_list.html");
            }
            break;
            case "/server/user/get": {
                String payload = mbmRequest.getPayload();
                System.out.println("/server/user/get");
                System.out.println(payload);
                User userId = JSONObject.parseObject(payload, User.class);
                User user = userServiceSql.getUserById(userId.getId());
                responseJson(user);
            }
            break;
            case "/server/user/delete": {
//                modifyAble();
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                User userByIp = logins.getUserByIp(hostAddress);
                if(userByIp.getUserType()!=1){
                    throw new ValidationException("权限不足");
                }
                String payload = mbmRequest.getPayload();
                User userId = JSONObject.parseObject(payload, User.class);
                userServiceSql.deleteUser(userId);
                responseOk();
            }
            break;
            case "/server/provider/modify": {//添加供应商信息
                Map<String, String> formData = mbmRequest.getFormData();
                String data = JSONObject.toJSONString(formData);
                Provider provider = null;
                try {
                    provider = JSONObject.parseObject(data, Provider.class);
                } catch (Exception e) {
                    throw new BadRequestException("输入不能为空");
                }
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                User userByIp = logins.getUserByIp(hostAddress);
                if(userByIp.getUserType()!=1){
                    throw new ValidationException("权限不足");
                }
                if (provider.getId() == 0) {
                    providerServiceSql.addProvider(provider);
                } else {
                    providerServiceSql.updateProvider(provider);
                }
                responseRedirect(mbmRequest, "/provider_list.html");
                break;
            }
            case "/server/provider/list": {//显示供应商列表
                List<Provider> providerList;
                // 如果payload不为null，表示需要根进行检索
                String payload = mbmRequest.getPayload();
                if (payload == null) {
                    providerList = providerServiceSql.getProviderList();
                } else {
                    // 将待检索的用户名转换为provider对象
                    Provider provider = JSONObject.parseObject(payload, Provider.class);
                    providerList = providerServiceSql.getProviderList(provider);
                }
                responseJson(providerList);
                break;
            }
            case "/server/provider/delete": {//删除供应商
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                User userByIp = logins.getUserByIp(hostAddress);
                if(userByIp.getUserType()!=1){
                    throw new ValidationException("权限不足");
                }
                String payload = mbmRequest.getPayload();
                Provider provider = JSONObject.parseObject(payload, Provider.class);
                providerServiceSql.deleteProvider(provider);
                responseOk();
                break;
            }
            case "/server/provider/get": {//更新供应商信息，自动填充更新界面信息
                String payload = mbmRequest.getPayload();
                Provider provider = JSONObject.parseObject(payload, Provider.class);
                Provider providerById = providerServiceSql.getProviderById(provider.getId());
                responseJson(providerById);
                break;
            }
            case "/server/bill/list": {
                String payload = mbmRequest.getPayload();
                List<Bill> bills;
                if (payload == null) {
                    bills = billServiceSql.getBillList();
                } else {//根据传入的对象进行检索 获取模糊查询的列表
                    Bill bill = JSONObject.parseObject(payload, Bill.class);
                    System.out.println(payload);
                    System.out.println(bill.getIsPay() + bill.getIsPayStr());
                    bills = billServiceSql.getBillList(bill);
                }
                responseJson(bills);
                break;
            }
            case "/server/bill/modify": {
                Map<String, String> formData = mbmRequest.getFormData();
                String string = JSONObject.toJSONString(formData);
                System.out.println(string);
                Bill bill = null;
                try {
                    bill = JSONObject.parseObject(string, Bill.class);
                } catch (Exception e) {
                    throw new BadRequestException("输入不能为空");
                }
                System.out.println(bill);
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                User userByIp = logins.getUserByIp(hostAddress);
                if(userByIp.getUserType()!=1){
                    throw new ValidationException("权限不足");
                }
                if (bill.getId() == 0) {//新增
                    billServiceSql.addBill(bill);
                } else {//更新
                    System.out.println(string);
                    billServiceSql.updateBill(bill);
                }
                responseRedirect(mbmRequest, "/bill_list.html");
                break;
            }
            case "/server/bill/get": {
                String payload = mbmRequest.getPayload();
                Bill bill = JSONObject.parseObject(payload, Bill.class);
                Bill billById = billServiceSql.getBillById(bill.getId());
                responseJson(billById);
                break;
            }
            case "/server/bill/delete": {
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                User userByIp = logins.getUserByIp(hostAddress);
                if(userByIp.getUserType()!=1){
                    throw new ValidationException("权限不足");
                }
                String payload = mbmRequest.getPayload();
                Bill bill = JSONObject.parseObject(payload, Bill.class);
                billServiceSql.deleteBill(bill);
                responseOk();
                break;
            }
            case "/server/exit":{
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                logins.deleteUser(hostAddress);
                responseRedirect(mbmRequest, "/login.html");
            }
        }
    }

    private void responseJson(Object object) throws IOException {
        String data = JSONObject.toJSONString(object);
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
        out.writeBytes("HTTP/1.1 200 OK");
        out.writeBytes("\r\n");
        out.writeBytes("Content-Length: " + data.getBytes().length);
        out.writeBytes("\r\n");
        out.writeBytes("Content-Type: application/json;charset=utf-8;");
        out.writeBytes("\r\n");
        out.writeBytes("\r\n");
        out.write(data.getBytes());
    }

    private void responseOk() throws IOException {
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        out.writeBytes("HTTP/1.1 200 OK");
        out.writeBytes("\r\n");
        out.writeBytes("\r\n");
    }

    private void responseFailJson(String data) throws IOException {
        // String data = JSONObject.toJSONString(object);
        DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

        out.writeBytes("HTTP/1.1 400 Bad request");
        out.writeBytes("\r\n");
        out.writeBytes("Content-Length: " + data.getBytes().length);
        out.writeBytes("\r\n");
        out.writeBytes("Content-Type: application/json;charset=utf-8;");
        out.writeBytes("\r\n");
        out.writeBytes("\r\n");
        out.write(data.getBytes());
    }

    private void responseRedirect(MbmRequestSql request, String path)
            throws IOException {
        OutputStream outputStream = clientSocket.getOutputStream();
        outputStream.write("HTTP/1.1 302 Found".getBytes());
        outputStream.write("\r\n".getBytes());
        // 告知浏览器请求结束，需要再次向请求给定的路径发起请求
        outputStream.write(("Location: " + "http://" + request.getHost() + path).getBytes());
        outputStream.write("\r\n".getBytes());
    }

    private void responseResource(MbmRequestSql mbmRequestSql) throws IOException {
        String resourcePath = mbmRequestSql.getPath();
        if (resourcePath.startsWith("/")) {
            resourcePath = resourcePath.substring(1);
        }

        if (resourcePath.length() == 0 ) {
            resourcePath = "pages/login.html";
        } else if (resourcePath.startsWith("css") || resourcePath.startsWith("images") || resourcePath
                .startsWith("js") || resourcePath.contains(".html")) {
            resourcePath = "pages/" + resourcePath;
        }

        // form_post_fail.html?msg=异常信息描述
        if (resourcePath.contains("form_post_fail.html")) {
            // 获取异常信息描述
            InputStream resourceAsStream = HttpServer.class.getClassLoader()
                    .getResourceAsStream(resourcePath);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
            StringBuilder builder = new StringBuilder();

            String line = bufferedReader.readLine();
            while (line != null) {
                if (line.contains("${msg}")) {
                    line = line.replace("${msg}", mbmRequestSql.getFormData().get("msg"));
                }
                builder.append(line);
                builder.append(System.lineSeparator());
                line = bufferedReader.readLine();
            }
            byte[] data = builder.toString().getBytes();
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
            if (resourcePath.contains(".html")) {
                outputStream.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
            }

            String contentLength = "Content-Length: " + data.length;
            outputStream.write(contentLength.getBytes());

            outputStream.write("\r\n".getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(data);
            bufferedReader.close();
            return;
        }
        ////////
        if (resourcePath.contains(".html")) {
            if(resourcePath.equals("login.html")){
                String hostAddress = clientSocket.getInetAddress().getHostAddress();
                logins.deleteUser(hostAddress);
            }
            InputStream inputStream = HttpServer.class.getClassLoader().getResourceAsStream(resourcePath);
            if (inputStream == null) {
                inputStream = HttpServer.class.getClassLoader().getResourceAsStream("pages/404.html");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String s = bufferedReader.readLine();
            StringBuilder stringBuilder = new StringBuilder();
            ////////////////
            String ip = clientSocket.getInetAddress().getHostAddress();
            System.out.println(ip);
            User userByIp = logins.getUserByIp(ip);
            String name = userByIp.getName();
            /////////////////
            while (s != null) {
                if (s.contains("<div class=\"welcome\">")) {
                    if (name != null) {
                        s = s.replace("admin", name);
                    }
                }
                stringBuilder.append(s);
//            stringBuilder.append(System.lineSeparator());
                stringBuilder.append("\n");
                s = bufferedReader.readLine();
            }
            OutputStream outputStream = clientSocket.getOutputStream();
            outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
            if (resourcePath.contains(".html")) {
                outputStream.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
            }
            String contentLength = "Content-Length: " + stringBuilder.toString().getBytes().length;
            outputStream.write(contentLength.getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write("\r\n".getBytes());
            outputStream.write(stringBuilder.toString().getBytes());
            inputStream.close();
            return;
        }


///////
        InputStream resourceAsStream = HttpServer.class.getClassLoader()
                .getResourceAsStream(resourcePath);
        if (resourceAsStream == null) {
            resourceAsStream = HttpServer.class.getClassLoader()
                    .getResourceAsStream("pages/404.html");
        }

        OutputStream outputStream = clientSocket.getOutputStream();

        outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
        if (resourcePath.contains(".html")) {
            outputStream.write("Content-Type: text/html; charset=utf-8\r\n".getBytes());
        }

        String contentLength = "Content-Length: " + resourceAsStream.available();
        outputStream.write(contentLength.getBytes());

        outputStream.write("\r\n".getBytes());
        outputStream.write("\r\n".getBytes());
        outputStream.write(resourceAsStream.readAllBytes());
        resourceAsStream.close();
    }

//    public static void modifyAble() {
//        String ip = clientSocket.getInetAddress().getHostAddress();
//        User userByIp = logins.getUserByIp(ip);
//        String name = userByIp.getName();
//        if (SocketHandlerSql.user.getUserType() != 1) {
//            throw new FormPostException("权限不足");
//        }
//    }
}
