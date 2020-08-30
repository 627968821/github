package club.banyuan.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private int userId;
    private String userName;
    private String password;
    private String IDCard;
    private String phone;
    private String address;
    private String pastCode;
    private List<Order> orderList;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", IDCard='" + IDCard + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", pastCode='" + pastCode + '\'' +
                ", orderList=" + orderList +
                '}';
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPastCode() {
        return pastCode;
    }

    public void setPastCode(String pastCode) {
        this.pastCode = pastCode;
    }

}
