package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.OrderDetailDAO;
import db.DBConnection;
import entity.OrderDetail;
import entity.OrderDetailPK;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    public  List<Object> findAll() {
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM `OrderDetail`");
            List<Object> orderDetails = new ArrayList<>();
            while (rst.next()) {
                orderDetails.add(new OrderDetail(rst.getString(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getBigDecimal(4)));
            }
            return orderDetails;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public  Object find(Object key) {
        OrderDetailPK orderDetailPK = (OrderDetailPK) key;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM `OrderDetail` WHERE orderId=? AND itemCode=?");
            pstm.setObject(1, orderDetailPK.getOrderId());
            pstm.setObject(2, orderDetailPK.getItemCode());
            ResultSet rst = pstm.executeQuery();
            if (rst.next()) {
                return new OrderDetail(rst.getString(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getBigDecimal(4));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public  boolean save(Object entity) {
        OrderDetail orderDetail = (OrderDetail) entity;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `OrderDetail` VALUES (?,?,?,?)");
            pstm.setObject(1, orderDetail.getOrderDetailPK().getOrderId());
            pstm.setObject(2, orderDetail.getOrderDetailPK().getItemCode());
            pstm.setObject(3, orderDetail.getQty());
            pstm.setObject(4, orderDetail.getUnitPrice());
            return pstm.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public  boolean update(Object entity) {
        OrderDetail orderDetail = (OrderDetail) entity;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement("UPDATE OrderDetail SET qty=?, unitPrice=? WHERE orderId=? AND itemCode=?");
            pstm.setObject(3, orderDetail.getOrderDetailPK().getOrderId());
            pstm.setObject(4, orderDetail.getOrderDetailPK().getItemCode());
            pstm.setObject(1, orderDetail.getQty());
            pstm.setObject(2, orderDetail.getUnitPrice());
            return pstm.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public  boolean delete(Object key) {
        OrderDetailPK orderDetailPK = (OrderDetailPK) key;
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.
                    prepareStatement("DELETE FROM `OrderDetail` WHERE orderId=? AND itemCode=?");
            pstm.setObject(1, orderDetailPK.getOrderId());
            pstm.setObject(2, orderDetailPK.getItemCode());
            return pstm.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}