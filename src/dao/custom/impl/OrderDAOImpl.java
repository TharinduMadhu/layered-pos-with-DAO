package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.custom.CrudUtil;
import dao.custom.OrderDAO;
import db.DBConnection;
import entity.Order;

public class OrderDAOImpl implements OrderDAO {


    public String getLastOrderId() throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
        //   ResultSet rst = stm.executeQuery("SELECT * FROM `Order` ORDER BY id DESC LIMIT 1");
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order` ORDER BY id DESC LIMIT 1");
        if (!rst.next()) {
            return null;
        } else {
            return rst.getString(1);
        }
    }

    @Override
    public List<Order> findAll() throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery("SELECT * FROM `Order`");
        ResultSet rst = CrudUtil.execute("SELECT * FROM `Order`");
        List<Order> orders = new ArrayList<>();
        while (rst.next()) {
            orders.add(new Order(rst.getString(1),
                    rst.getDate(2),
                    rst.getString(3)));
        }
        return orders;
    }

    @Override
    public Order find(String key) throws Exception {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM `Order` WHERE id=?");
//            pstm.setObject(1, key);
//            ResultSet rst = pstm.executeQuery();
            ResultSet rst = CrudUtil.execute("SELECT * FROM `Order` WHERE id=?", key);
            if (rst.next()) {
                return new Order(rst.getString(1),
                        rst.getDate(2),
                        rst.getString(3));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Order order) throws Exception {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `Order` VALUES (?,?,?)");
//            pstm.setObject(1, order.getId());
//            pstm.setObject(2, order.getDate());
//            pstm.setObject(3, order.getCustomerId());
            return CrudUtil.execute("INSERT INTO `Order` VALUES (?,?,?)", order.getId(), order.getDate()
                    , order.getCustomerId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Order order) throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("UPDATE Order SET date=?, customerId=? WHERE id=?");
//            pstm.setObject(3, order.getId());
//            pstm.setObject(1, order.getDate());
//            pstm.setObject(2, order.getCustomerId());
        // return pstm.executeUpdate() > 0;
        return CrudUtil.execute("UPDATE Order SET date=?, customerId=? WHERE id=?", order.getId(),
                order.getDate(), order.getCustomerId());
    }

    @Override
    public boolean delete(String key) throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
////            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Order WHERE id=?");
////            pstm.setObject(1, key);
        return CrudUtil.execute("DELETE FROM Order WHERE id=?", key);
    }
}
