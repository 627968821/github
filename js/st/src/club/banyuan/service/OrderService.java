package club.banyuan.service;

import club.banyuan.pojo.Order;
import club.banyuan.pojo.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {
    public Order createOrder(Order order, List<OrderDetail> orderDetailList);
    public List<Order> getOrderListByUserId(Integer id) throws Exception;
    public List<OrderDetail> getOrderDetailListByOrderId(Integer id) throws Exception;
}
