package club.banyuan.mbm.service;
import club.banyuan.mbm.entity.User;
import club.banyuan.mbm.exception.ValidationException;
import club.banyuan.mbm.uti.JdbcUtil;
import club.banyuan.mbm.uti.ValidationUtil;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
public class UserServiceSql {
    //登录判断
    public User login(String name,String pwd){
        User userByName = null;
            userByName = getUserByName(name);
        if(userByName.getName() == null){
            throw new ValidationException("用户名错误");
        }
        if(userByName.getPwd().equals(pwd)){
            return userByName;
        }
        return null;
    }
    //根据id获取user
    public User getUserById(int id) {
        String sql = "select id,name,pwd,pwdConfirm,userType,userTypeStr from User where id = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, id);
        User user = JSONObject.parseObject(JSONObject.toJSONString(map), User.class);
        return user;
    }

    //根据用户名获取User
    public User getUserByName(String name) {
        String sql = "select id,name,pwd,pwdConfirm,userType,userTypeStr from User where name = ?";
        Map<String, Object> map = JdbcUtil.queryOne(sql, name);
        User user = JSONObject.parseObject(JSONObject.toJSONString(map), User.class);
        return user;
    }
    //获取用户列表
    public List<User> getUserList() {
        String sql = "select id,name,pwd,pwdConfirm,userType,userTypeStr from User";
        List<Map<String, Object>> list = JdbcUtil.queryALL(sql);
        List<User> users = JSONObject.parseArray(JSONObject.toJSONString(list), User.class);
        return users;
    }

    //更新
    public void updateUser(User user) {
        ValidationUtil.validate(user);//检查字段合法性
        if(!user.getPwd().equals(user.getPwdConfirm())){
            throw new ValidationException("确认密码不一致");
        }
        User userById = getUserById(user.getId());
        if (userById != null) {
            userById.setName(user.getName());
            userById.setPwd(user.getPwd());
            userById.setPwdConfirm(user.getPwdConfirm());
            userById.setUserType(user.getUserType());
            userById.setUserTypeStr(user.getUserTypeStr());
            String sql = "update User set name = ?,pwd = ?,pwdConfirm = ?,userType = ?,userTypeStr = ? where id = ?";
            JdbcUtil.update(sql, user.getName(), user.getPwd(), user.getPwdConfirm(), user.getUserType(), user.getUserTypeStr(),user.getId());
        }
    }
    //新增
    public void addUser(User user){
        ValidationUtil.validate(user);//检查字段合法性
        String sql="insert into User(name,pwd,pwdConfirm,userType,userTypeStr)values(?,?,?,?,?)";
        JdbcUtil.update(sql,user.getName(), user.getPwd(), user.getPwdConfirm(), user.getUserType(), user.getUserTypeStr());
    }
    //查询
    public List<User> getUserList(User user) {
        String sql = "select id,name,pwd,pwdConfirm,userType,userTypeStr from User where name like ?";
        List<Map<String, Object>> list = JdbcUtil.queryALL(sql,"%"+user.getName().trim()+"%");
        List<User> users = JSONObject.parseArray(JSONObject.toJSONString(list), User.class);
        return users;
    }
    //删除
    public void deleteUser(User user){
        User userById = getUserById(user.getId());
        String sql="delete from User where id=?";
        JdbcUtil.update(sql,userById.getId());
    }
}
