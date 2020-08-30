package club.banyuan.dao;

import club.banyuan.pojo.AuctionItem;
import club.banyuan.vo.AuctionVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AuctionItemDao {
    @Select("select *from auctionItem where auctionId = #{id}")
    public AuctionItem getAuctionByAuctionId(int id);
    public List<AuctionVo> getAuctionListByAuctionId(Integer id);
}
