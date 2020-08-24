package club.banyuan.dao;

import club.banyuan.pojo.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDao extends IBaseDao{
    public Order addOrder(Order order);
    public List<Order> getOrderByUserId(Integer id) throws Exception;
}
