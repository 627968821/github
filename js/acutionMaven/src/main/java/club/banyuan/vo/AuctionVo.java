package club.banyuan.vo;

public class AuctionVo {
    private int auctionId;
    private String auctionName;
    private int orderId;
    private int userId;
    private String userName;

    @Override
    public String toString() {
        return "AuctionVo{" +
                "auctionId=" + auctionId +
                ", auctionName='" + auctionName + '\'' +
                ", orderId=" + orderId +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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
}
