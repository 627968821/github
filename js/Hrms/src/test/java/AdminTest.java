import club.banyuan.HrmServer;
import club.banyuan.dao.AdminDao;
import club.banyuan.pojo.Admin;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AdminTest {

    @Test
    public void updateTest() {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        int ikun33 = mapper.updateAdmin("ikun33", "123456", 9);
        System.out.println(ikun33);
    }

    @Test
    public void addTest() {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        int ikun20 = mapper.addAdmin("ikun336", "123456");
        System.out.println(ikun20);
        hrmServer.destroy();
    }

    @Test
    public void deleteTest() {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        List<Integer> idList = new ArrayList<>();
        idList.add(4);
        idList.add(6);
        int i = mapper.deleteAdminById(idList);
        System.out.println(i);
        hrmServer.destroy();

    }

    @Test
    public void getAllTest() {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        List<Admin> allAdmin = mapper.getAllAdmin();
        System.out.println(allAdmin);
    }

    @Test
    public void getByIdTest() {
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        Admin adminById = mapper.getAdminById(11);
        System.out.println(adminById);
    }
    @Test
    public void selectByName(){
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        List<Admin> adminByName = mapper.getAdminByName("%kun%");
        System.out.println(adminByName);
    }
    @Test
    public void login(){
        HrmServer hrmServer = new HrmServer();
        AdminDao mapper = hrmServer.getMapper(AdminDao.class);
        Admin ikun = mapper.login("ikun", "123456");
        System.out.println(ikun);
    }
}
