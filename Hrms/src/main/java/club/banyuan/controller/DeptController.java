package club.banyuan.controller;

import club.banyuan.dao.DeptDao;
<<<<<<< HEAD
import club.banyuan.dao.ImpDao;
=======
>>>>>>> fc5f787... 1
import club.banyuan.pojo.Dept;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
<<<<<<< HEAD
import com.alibaba.fastjson.JSONPObject;
=======
>>>>>>> fc5f787... 1
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {

    SqlSession session = null;
    DeptDao deptDao = null;

    public void init() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = builder.build(inputStream);
        session = sqlSessionFactory.openSession();
        deptDao = session.getMapper(DeptDao.class);
    }

    public void destroy() {
        //事务提交
        session.commit();
//        资源关闭
        session.close();
    }

    @RequestMapping(value = "dept/list")
    @ResponseBody
    public void getList(HttpServletRequest request, HttpServletResponse response) {
        List<Dept> allDept = null;
        Map<String, Object> rlt = new HashMap<>();
        try {
            init();
            allDept = deptDao.getAllDept();
            System.out.println(allDept);
//    List<Dept> depts = new ArrayList<>();
//    depts.add(new Dept(1, "dept", "desc"));
            rlt.put("total", allDept.size());
            rlt.put("code", 0);
            rlt.put("rows", allDept);
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();
            String string = JSONObject.toJSONString(rlt);
            System.out.println(string);
            writer.print(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
//    return rlt;
    }

    @RequestMapping(value = "dept/save")
    @ResponseBody
    public void addDept(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        init();
        String name = request.getParameter("name");
        System.out.println(name);
        String description = request.getParameter("description");
        String id = request.getParameter("id");
        System.out.println(description);
        PrintWriter writer = response.getWriter();
        if(id==null){
        Dept name1 = deptDao.getDeptByName(name);
        System.out.println(name1);
        if (name1 != null) {
            writer.print("{\"code\":1,\"message\":\"\"}");
        } else {
            int i = deptDao.addDept(name, description);
            destroy();
            writer.print("{\"code\":0,\"message\":\"\"}");
        }}else {
            //检验是否有同名
            Dept deptByNameAndId = deptDao.getDeptByNameAndId(name, Integer.parseInt(id));
            if (deptByNameAndId != null) {
                writer.print("{\"code\":1,\"message\":\"\"}");
            } else {
                deptDao.updateDeptById(name, description, Integer.parseInt(id));
                destroy();
                writer.print("{\"code\":0,\"message\":\"\"}");
            }
        }
    }

    @RequestMapping(value = "dept/delete")
    @ResponseBody
    public void deleteDept(HttpServletRequest request, HttpServletResponse response) throws IOException {
        init();
<<<<<<< HEAD
=======

>>>>>>> fc5f787... 1
        String ids = request.getParameter("ids");
        String[] split = ids.split(",");
        List<Integer> idList=new ArrayList<>();
        for (String s : split) {
            idList.add(Integer.parseInt(s));
        }
        String string = JSONObject.toJSONString(idList);
        System.out.println(string);
        int i = deptDao.deleteDeptById(idList);
        PrintWriter writer = response.getWriter();
        if(i!=0){
            writer.print("{\"code\":0,\"message\":\"操作成功\"}");
            destroy();
        }else {
            destroy();
            writer.print("{\"code\":\"1\",\"message\":\"删除失败\"}");
        }
    }
<<<<<<< HEAD
=======
    @RequestMapping(value = "dept/getcombobox")
    @ResponseBody
    public void getcombobox(HttpServletRequest request, HttpServletResponse response) throws IOException {
        init();
        response.setCharacterEncoding("UTF-8");
        List<Dept> allDept = deptDao.getAllDept();
        String string = JSONObject.toJSONString(allDept);
        PrintWriter writer = response.getWriter();
        writer.print(string);
        System.out.println(string);

    }

>>>>>>> fc5f787... 1
}
