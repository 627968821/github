package club.banyuan.pojo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Date birth;
    private List<UserAddress> userAddress;
    private Map<String,UserAddress> userAdrMap;
//    private String[] fav;
//
//    public String[] getFav() {
//        return fav;
//    }
//
//    public void setFav(String[] fav) {
//        this.fav = fav;
//    }

    private List<String> fav;

    public List<String> getFav() {
        return fav;
    }

    public void setFav(List<String> fav) {
        this.fav = fav;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public List<UserAddress> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(List<UserAddress> userAddress) {
        this.userAddress = userAddress;
    }

    public Map<String, UserAddress> getUserAdrMap() {
        return userAdrMap;
    }

    public void setUserAdrMap(Map<String, UserAddress> userAdrMap) {
        this.userAdrMap = userAdrMap;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birth=" + birth +
                ", userAddress=" + userAddress +
                ", userAdrMap=" + userAdrMap +
                ", fav=" + fav +
                '}';
    }
}
