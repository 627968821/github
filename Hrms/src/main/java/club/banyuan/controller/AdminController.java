package club.banyuan.controller;

import club.banyuan.HrmServer;
import club.banyuan.dao.AdminDao;
import club.banyuan.pojo.Admin;
import club.banyuan.pojo.Emp;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class AdminController {
    @RequestMapping(value = "admin/login")
    public String login(HttpServletRequest request, HttpSession session) throws IOException {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username+"   "+password);
        Admin login = mapper.login(username,password);
        session.setAttribute("login",login);
        System.out.println(login);
        if(login!=null){
            return "redirect:/home_page.html";
        }else
        return "redirect:/";
    }
    @RequestMapping("/")
    public String index() {
        return "login.html";
    }

    @RequestMapping(value = "admin/list")
    @ResponseBody
    public void  getAdminList(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("username");
        String page = request.getParameter("page");
        String rows = request.getParameter("rows");
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        PrintWriter writer = response.getWriter();
        List<Admin> allAdmin = mapper.getAllAdmin();
        int size = allAdmin.size();
        List<Admin> adminByName = mapper.getAdminByName("%"+name+"%");
        int size1 = adminByName.size();
        Map<String,Object> rlt=new HashMap<>();
        List<Admin> admins=null;
        if(name==null){
            if(size<=Integer.parseInt(rows)){
                admins = allAdmin;
            }else {
                admins = allAdmin.subList(Integer.parseInt(page), Integer.parseInt(rows)+1);
            }
            rlt.put("total",allAdmin.size());
            rlt.put("code",0);
            rlt.put("rows",admins);
            String string = JSONObject.toJSONString(rlt);
            writer.print(string);
            System.out.println(string);
        }else {
            if(size1<Integer.parseInt(rows)){
                admins = adminByName;
            }else {
                admins = adminByName.subList(Integer.parseInt(page), Integer.parseInt(rows)+1);
            }
            rlt.put("total",allAdmin.size());
            rlt.put("code",0);
            rlt.put("rows",admins);

            String string = JSONObject.toJSONString(rlt);
            writer.print(string);
            System.out.println(string);
        }

    }

    @RequestMapping("admin/save")
    @ResponseBody
    public void  saveAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        PrintWriter writer = response.getWriter();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String id = request.getParameter("id");
        if(id==null){
            int i = mapper.addAdmin(username, password);
            hrmServer.destroy();
        }else {
            int i = mapper.updateAdmin(username, password, Integer.parseInt(id));
            hrmServer.destroy();
        }
        writer.print("{\"code\":0,\"message\":\"\"}");

    }

    @RequestMapping("admin/delete")
    @ResponseBody
    public void  deleteAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        PrintWriter writer = response.getWriter();
        String id = request.getParameter("ids");
        List<Integer> idList=new ArrayList<>();
        String[] split = id.split(",");
        for (String s : split) {
            idList.add(Integer.parseInt(s));
        }
        mapper.deleteAdminById(idList);
        writer.print("{\"code\":0,\"message\":\"\"}");
        hrmServer.destroy();

    }

    @RequestMapping("admin/getcombobox")
    @ResponseBody
    public void getcombobox(HttpServletRequest request, HttpServletResponse response){
    }

    @RequestMapping("admin/info")
    @ResponseBody
    public void adminInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("login");
        System.out.println(admin);
        String username = admin.getUsername();
        PrintWriter writer = response.getWriter();
        writer.print("{\"code\":0,\"message\":\"\",\"username\":\""+username+"\"}");

    }
    @RequestMapping("admin/logout")
    public String adminOut(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
//        session.invalidate();
        return "redirect:/";
}


}
