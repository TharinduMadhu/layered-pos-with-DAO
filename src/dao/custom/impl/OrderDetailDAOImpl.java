package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.custom.CrudUtil;
import dao.custom.OrderDetailDAO;
import db.DBConnection;
import entity.OrderDetail;
import entity.OrderDetailPK;

public class OrderDetailDAOImpl implements OrderDetailDAO {


    @Override
    public List<OrderDetail> findAll() throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
            // ResultSet rst = stm.executeQuery("SELECT * FROM `OrderDetail`");
             ResultSet rst = CrudUtil.execute("SELECT * FROM `OrderDetail");
            List<OrderDetail> orderDetails = new ArrayList<>();
            while (rst.next()) {
                orderDetails.add(new OrderDetail(rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getBigDecimal(4)));
            }
            return orderDetails;
        }

    @Override
    public OrderDetail find(OrderDetailPK key) throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM `OrderDetail` WHERE orderId=? AND itemCode=?");
//            pstm.setObject(1, key.getOrderId());
//            pstm.setObject(2, key.getItemCode());
        ResultSet rst = CrudUtil.execute("SELECT * FROM `OrderDetail` WHERE orderId=? AND itemCode=?", key.getOrderId(),
                key.getItemCode());
        if (rst.next()) {
            return new OrderDetail(rst.getString(1),
                    rst.getString(2),
                    rst.getInt(3),
                    rst.getBigDecimal(4));
        }
        return null;
    }

    @Override
    public boolean save(OrderDetail orderDetail) throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO `OrderDetail` VALUES (?,?,?,?)");
//            pstm.setObject(1, orderDetail.getOrderDetailPK().getOrderId());
//            pstm.setObject(2, orderDetail.getOrderDetailPK().getItemCode());
//            pstm.setObject(3, orderDetail.getQty());
//            pstm.setObject(4, orderDetail.getUnitPrice());

            return CrudUtil.execute("INSERT INTO `OrderDetail` VALUES (?,?,?,?)", orderDetail.getOrderDetailPK().getOrderId(),
                    orderDetail.getOrderDetailPK().getItemCode(),orderDetail.getQty(),orderDetail.getUnitPrice());
        }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
////            PreparedStatement pstm = connection.prepareStatement("UPDATE OrderDetail SET qty=?, unitPrice=? WHERE orderId=? AND itemCode=?");
////            pstm.setObject(3, orderDetail.getOrderDetailPK().getOrderId());
////            pstm.setObject(4, orderDetail.getOrderDetailPK().getItemCode());
////            pstm.setObject(1, orderDetail.getQty());
////            pstm.setObject(2, orderDetail.getUnitPrice());

            return CrudUtil.execute("UPDATE OrderDetail SET qty=?, unitPrice=? WHERE orderId=? AND itemCode=?", orderDetail.getOrderDetailPK().getOrderId(),
                    orderDetail.getOrderDetailPK().getItemCode(),orderDetail.getQty(),orderDetail.getUnitPrice());
        }


    @Override
    public boolean delete(OrderDetailPK orderDetailPK) throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.
//                prepareStatement("DELETE FROM `OrderDetail` WHERE orderId=? AND itemCode=?");
//            pstm.setObject(1, orderDetailPK.getOrderId());
//            pstm.setObject(2, orderDetailPK.getItemCode());
            return  CrudUtil.execute("DELETE FROM `OrderDetail` WHERE orderId=? AND itemCode=?", orderDetailPK.getOrderId(),
                    orderDetailPK.getItemCode());
        }
    }
