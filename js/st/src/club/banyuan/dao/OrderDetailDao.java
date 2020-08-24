package club.banyuan.dao;

import club.banyuan.pojo.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDao extends IBaseDao{
    public OrderDetail addOrderDetail(OrderDetail orderDetail);
    public List<OrderDetail> getOrderDetailListOrderId(Integer id) throws Exception;
}
