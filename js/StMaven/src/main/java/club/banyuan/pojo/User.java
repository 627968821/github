package club.banyuan.pojo;

public class User {
    private Integer id;
    private String loginName;
    private String userName;
    private String password;
    private Integer sex;
    private String email;
    private String mobile;
    private String identitycode;
    private Integer type;

    public User() {
    }

    public User(Integer id, String loginName, String userName, String password, Integer sex, String email, String mobile) {
        this.id = id;
        this.loginName = loginName;
        this.userName = userName;
        this.password = password;
        this.sex = sex;
        this.email = email;
        this.mobile = mobile;
    }

    public String getIdentitycode() {
        return identitycode;
    }

    public void setIdentitycode(String identitycode) {
        this.identitycode = identitycode;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {return id;}

    public void setId(Integer id) {this.id = id;}

    public String getLoginName() { return loginName; } 

    public void setLoginName(String loginName) { this.loginName = loginName; } 

    public String getUserName() { return userName; } 

    public void setUserName(String userName) { this.userName = userName; } 

    public String getPassword() { return password; } 

    public void setPassword(String password) { this.password = password; } 

    public Integer getSex() { return sex; } 

    public void setSex(Integer sex) { this.sex = sex; } 

    public String getEmail() { return email; } 

    public void setEmail(String email) { this.email = email; } 

    public String getMobile() { return mobile; } 

    public void setMobile(String mobile) { this.mobile = mobile; }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", loginName='" + loginName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
