package club.banyuan.mbm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Bill {
    private int id;
    private int providerId;
    private int money;
    private String product;//商品描述
    private int isPay;
    private String isPayStr;
    private String updateTime;

    public String getIsPayStr() {
        return isPayStr;
    }

    public void setIsPayStr() {
        if(isPay==1){
            isPayStr="已付";
        }else {
            if(isPay==0){
                isPayStr="未付";
            }
        }
    }


    public String getUpdateTime() {
        return updateTime;
    }

    public void setIsPayStr(String isPayStr) {
        this.isPayStr = isPayStr;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void setUpdateTime() {
        SimpleDateFormat time = new SimpleDateFormat();
        time.applyPattern("yyyy-MM-dd HH:mm:ss a");
        Date date = new Date();
        updateTime=time.format(date);
    }

    private String providerName;

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProviderId() {
        return providerId;
    }

    public void setProviderId(int providerId) {
        this.providerId = providerId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getIsPay() {
        return isPay;
    }

    public void setIsPay(int isPay) {
        this.isPay = isPay;
    }
}
