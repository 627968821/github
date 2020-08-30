package club.banyuan.pojo;

import java.io.Serializable;
import java.util.List;

public class AuctionItem implements Serializable {
    /**
     * CREATE TABLE `auctionitem` (
     *   `auctionId` int(11) NOT NULL AUTO_INCREMENT,
     *   `auctionName` varchar(255) DEFAULT NULL,
     *   `startPrice` int(11) DEFAULT NULL,
     *   `lowPrice` int(11) DEFAULT NULL,
     *   `startTime` varchar(255) DEFAULT NULL,
     *   `endTime` varchar(255) DEFAULT NULL,
     *   `pictureUrl` varchar(255) DEFAULT NULL,
     *   `desc` varchar(255) DEFAULT NULL,
     *   PRIMARY KEY (`auctionId`) USING BTREE
     * ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
     */
    private int auctionId;
    private String auctionName;
    private int startPrice;
    private int lowPrice;
    private String startTime;
    private String endTime;
    private String pictureUrl;
    private String desc;

    @Override
    public String toString() {
        return "AuctionItem{" +
                "auctionId=" + auctionId +
                ", auctionName='" + auctionName + '\'' +
                ", startPrice=" + startPrice +
                ", lowPrice=" + lowPrice +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", desc='" + desc + '\'' +
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

    public int getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(int startPrice) {
        this.startPrice = startPrice;
    }

    public int getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(int lowPrice) {
        this.lowPrice = lowPrice;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
