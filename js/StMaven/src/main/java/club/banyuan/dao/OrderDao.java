package club.banyuan.dao;

import club.banyuan.pojo.Order;

import java.util.List;

public interface OrderDao {
    public int addOrder(Order order);
    public List<Order> getOrderByUserId(Integer id);
}
