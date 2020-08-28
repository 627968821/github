package club.banyuan.dao;

import club.banyuan.pojo.OrderDetail;

import java.util.List;

public interface OrderDetailDao {
    public int addOrderDetail(OrderDetail orderDetail);
    public List<OrderDetail> getOrderDetailListOrderId(Integer id);
}
