package club.banyuan.dao.impl;

import club.banyuan.dao.OrderDao;
import club.banyuan.pojo.Order;

import java.sql.Connection;
import java.sql.ResultSet;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    public  OrderDaoImpl(Connection conn){
        super(conn);
    }
    @Override
    public Object tableToClass(ResultSet rs) throws Exception {
        Order order =new Order();

        return null;
    }

    @Override
    public Order addOrder(Order order) {
        String sql = "insert into shoppingstreet.order values(?, ?, ?, ?, ?, ?, ?)";
        Object[] param = new Object[7];
        param[0] = null;
        param[1] = order.getUserId();
        param[2] = order.getLoginName();
        param[3] = order.getUserAddress();
        param[4] = order.getCreateTime();
        param[5] = order.getCost();
        param[6] = order.getSerialNumber();
        int id = executeInsert(sql, param);
        order.setId(id);
        closeResource();
        return order;
    }
}
