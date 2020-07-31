package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.custom.CrudUtil;
import dao.custom.CustomerDAO;
import db.DBConnection;
import entity.Customer;

public class CustomerDAOImpl implements CustomerDAO {



    public  String getLastCustomerId() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer ORDER BY id DESC LIMIT 1");
            if (!rst.next()){
                return null;
            }else{
                return rst.getString(1) ;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Customer> findAll() {
        try {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer");
            List <Customer> customers = new ArrayList<>();
            while (rst.next()){
                customers.add(new Customer(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)));
            }
            return customers;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer find(String key) {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
            //pstm.setObject(1, key);
            ResultSet rst = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", key);
            if (rst.next()){
                return new Customer(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3));
            }
            return null;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean save(Customer customer) {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?)");
//            pstm.setObject(1, customer.getId());
//            pstm.setObject(2, customer.getName());
//            pstm.setObject(3, customer.getAddress());
            return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?)", customer.getId(),customer.getName(),
                     customer.getAddress());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Customer customer) {
        try {

            return CrudUtil.execute("UPDATE Customer SET name=?, address=? WHERE id=?", customer.getId(),customer.getName()
                   , customer.getAddress());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String key) {
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("DELETE FROM Customer WHERE id=?");
//            pstm.setObject(1, key);

            return CrudUtil.execute("DELETE FROM Customer WHERE id=?", key);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }
}
