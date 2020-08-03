package club.banyuan.mbm.service;

import club.banyuan.mbm.entity.User;
import club.banyuan.mbm.uti.JdbcUtil;
import com.alibaba.fastjson.JSONObject;

import java.util.Map;

public class Logins {
    public User getUserByIp(String ip) {
        String sql = "select id,name,pwd,pwdConfirm,userType,userTypeStr,ip from Logins where ip = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, ip);
        User user = JSONObject.parseObject(JSONObject.toJSONString(map), User.class);
        return user;
    }

    //新增
    public void addUser(User user) {
        User userById = getUserById(user.getId());
        if (userById != null) {
            deleteUserById(userById.getId());
        }
        String sql = "insert into Logins(ip,id,name,pwd,pwdConfirm,userType,userTypeStr)values(?,?,?,?,?,?,?)";
        JdbcUtil.update(sql, user.getIp(), user.getId(), user.getName(), user.getPwd(), user.getPwdConfirm(), user.getUserType(), user.getUserTypeStr());
    }

    public void deleteUser(String ip) {
        User userByIp = getUserByIp(ip);
        String sql = "delete from Logins where ip=?";
        JdbcUtil.update(sql, userByIp.getIp());
    }

    public void deleteUserById(int id) {
        User userByIp = getUserById(id);
        String sql = "delete from Logins where id=?";
        JdbcUtil.update(sql, userByIp.getId());
    }

    public User getUserById(int id) {
        String sql = "select id,name,pwd,pwdConfirm,userType,userTypeStr from Logins where id = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, id);
        User user = JSONObject.parseObject(JSONObject.toJSONString(map), User.class);
        return user;
    }
}
