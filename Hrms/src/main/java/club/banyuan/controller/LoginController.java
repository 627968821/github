package club.banyuan.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  @RequestMapping("/admin/login")
  public String login(String username, String password) {
    System.out.println(username);
    System.out.println(password);
    return "redirect:/home_page.html";
  }

  @RequestMapping("/")
  public String index() {
    return "login.html";
  }
}
