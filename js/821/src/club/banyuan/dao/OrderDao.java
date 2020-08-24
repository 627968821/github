package club.banyuan.dao;

import club.banyuan.pojo.Order;

public interface OrderDao extends IBaseDao{
    public Order addOrder(Order order);
}
