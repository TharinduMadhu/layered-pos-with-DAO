package dao.custom.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.custom.CrudUtil;
import dao.custom.ItemDAO;
import db.DBConnection;
import entity.Item;

public class ItemDAOImpl implements ItemDAO {


    public  String getLastItemCode() throws Exception{
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
             ResultSet rst = CrudUtil.execute("SELECT * FROM Item ORDER BY code DESC LIMIT 1");
            if (!rst.next()){
                return null;
            }else{
                return rst.getString(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Item> findAll() throws Exception{
        try {
//            Connection connection = DBConnection.getInstance().getConnection();
//            Statement stm = connection.createStatement();
          //  ResultSet rst = stm.executeQuery("SELECT * FROM Item");
            ResultSet rst =CrudUtil.execute("SELECT * FROM Item");
            List<Item> items = new ArrayList<>();
            while (rst.next()){
                items.add(new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4)));
            }
            return items;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Item find(String key) throws Exception{

//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
//            pstm.setObject(1, key);

            ResultSet rst = CrudUtil.execute("SELECT * FROM Item WHERE code=?", key);
            if (rst.next()){
                return new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4));
            }
            return null;
        }


    @Override
    public boolean save(Item item) throws Exception {
//            Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?)");
//            pstm.setObject(1, item.getCode());
//            pstm.setObject(2, item.getDescription());
//            pstm.setObject(3, item.getUnitPrice());
//            pstm.setObject(4, item.getQtyOnHand());
            return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)", item.getCode(),
                    item.getDescription(),item.getUnitPrice(),item.getQtyOnHand());
        }

    @Override
    public boolean update(Item item) throws Exception {
         //   Connection connection = DBConnection.getInstance().getConnection();
//            PreparedStatement pstm = connection.prepareStatement("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?");
//            pstm.setObject(4, item.getCode());
//            pstm.setObject(1, item.getDescription());
//            pstm.setObject(2, item.getUnitPrice());
//            pstm.setObject(3, item.getQtyOnHand());
            return CrudUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?",item.getCode(),item.getDescription(),
                    item.getUnitPrice(),item.getQtyOnHand());
        }

    @Override
    public boolean delete(String key) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE code=?", key);
    }
}
