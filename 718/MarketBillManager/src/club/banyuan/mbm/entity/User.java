package club.banyuan.mbm.entity;

public class User {

  private int id;
  @Validation(regex = "[^!@#$%^&*()]{2,10}", msg = "用户名不合法,或输入为空",isRequired = true)
  private String name;
  @Validation(regex = "\\w{3,15}", msg = "密码不合法，或输入为空",isRequired = true)
  private String pwd;
  @Validation(regex ="\\w{3,15}",msg="确认密码不合法，或输入为空",isRequired =true)
  private String pwdConfirm;
  private int userType;
  // 如果userType = 0，str = 普通用户
  // 如果userType = 1，str = 经理
  private String userTypeStr;
  private String ip;

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPwd() {
    return pwd;
  }

  public void setPwd(String pwd) {
    this.pwd = pwd;
  }

  public String getPwdConfirm() {
    return pwdConfirm;
  }

  public void setPwdConfirm(String pwdConfirm) {
    this.pwdConfirm = pwdConfirm;
  }

  public int getUserType() {
    return userType;
  }

  public void setUserType(int userType) {
    this.userType = userType;
    if (userType == 0) {
      userTypeStr = "普通用户";
    } else if (userType == 1) {
      userTypeStr = "经理";
    }
  }

  public String getUserTypeStr() {
    return userTypeStr;
  }

  public void setUserTypeStr(String userTypeStr) {
    this.userTypeStr = userTypeStr;
  }
}
