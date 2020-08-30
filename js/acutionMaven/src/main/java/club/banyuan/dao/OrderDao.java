package club.banyuan.dao;

import club.banyuan.pojo.Order;
import club.banyuan.pojo.SaledAuction;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrderDao {
    @Select("select *from orders where userId=#{userId}")
    public List<Order> findOrderByUserId(int id);

    public List<SaledAuction> getAllSaledAuction();

    @Select("SELECT s.id,s.endPrice,s.userId,a.*,u.userName FROM \n" +
            "saledauction s INNER JOIN `user` u on u.userId=s.userId inner JOIN\n" +
            "auctionitem a on a.auctionId=s.auctionId WHERE s.auctionId=#{s.auctionId}")
    @Results(id = "auctionMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "endPrice", property = "endPrice"),
            @Result(column = "userName", property = "userName"),
            @Result(column = "userId", property = "userId"),
            @Result(column = "auctionId", property = "auctionItem",
                    one =@One(select = "club.banyuan.dao.AuctionItemDao.getAuctionByAuctionId",fetchType = FetchType.EAGER)
            )
    })
    public SaledAuction getSaledAuctionByAuctionId(Integer id);
}
