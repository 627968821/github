package club.banyuan.controller;

import club.banyuan.pojo.Dept;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DeptController {

  @RequestMapping(value = "/dept/list")
  @ResponseBody
  public Map<String, Object> getList(String name) {
    Map<String, Object> rlt = new HashMap<>();
    List<Dept> depts = new ArrayList<>();
    depts.add(new Dept(1, "dept", "desc"));
    rlt.put("total", 1);
    rlt.put("code", 0);
    rlt.put("rows", depts);
    return rlt;

  }
}
