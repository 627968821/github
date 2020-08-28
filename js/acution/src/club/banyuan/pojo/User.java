package club.banyuan.pojo;

public class User {
    private int userId;
    private String userName;
    private String password;
    private String IDCard;
    private String phone;
    private String address;
    private String pastCode;

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
                '}';
    }
}
