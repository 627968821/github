package club.banyuan.pojo;

import java.io.Serializable;

/**
 *CREATE TABLE `saledauction` (
 *   `auctionName` varchar(255) DEFAULT NULL,
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `auctionId` int(11) DEFAULT NULL,
 *   `startTime` datetime DEFAULT NULL,
 *   `endTime` datetime DEFAULT NULL,
 *   `startPrice` int(11) DEFAULT NULL,
 *   `endPrice` int(11) DEFAULT NULL,
 *   `userName` varchar(255) DEFAULT NULL,
 *   `userId` int(11) DEFAULT NULL,
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
 */

public class SaledAuction implements Serializable {
    private int id;
    private int endPrice;
    private String userName;
    private Integer userId;
    private AuctionItem auctionItem;

    @Override
    public String toString() {
        return "SaledAuction{" +
                "id=" + id +
                ", endPrice=" + endPrice +
                ", userName='" + userName + '\'' +
                ", userId=" + userId +
                ", auctionItem=" + auctionItem +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(int endPrice) {
        this.endPrice = endPrice;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public AuctionItem getAuctionItem() {
        return auctionItem;
    }

    public void setAuctionItem(AuctionItem auctionItem) {
        this.auctionItem = auctionItem;
    }
}
