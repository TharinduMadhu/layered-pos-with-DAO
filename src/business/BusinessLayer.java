package business;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DataLayer;
import db.DBConnection;
import util.CustomerTM;
import util.ItemTM;
import util.OrderDetailTM;
import util.OrderTM;

public class BusinessLayer {

  public static List<CustomerTM> getAllCustomers(){
    return DataLayer.getAllCustomers();
  }

  public static boolean saveCustomer(String id, String name, String address){
    return DataLayer.saveCustomer(new CustomerTM(id,name,address));
  }

  public static boolean deleteCustomer(String customerId){
    return DataLayer.deleteCustomer(customerId);
  }

  public static boolean updateCustomer(String name, String address, String customerId){
    return DataLayer.updateCustomer(new CustomerTM(customerId, name, address));
  }

  public static List<ItemTM> getAllItems(){
    return DataLayer.getAllItems();
  }

  public static boolean saveItem(String code, String description, int qtyOnHand, double unitPrice){
    return DataLayer.saveItem(new ItemTM(code, description, qtyOnHand, unitPrice));
  }

  public static boolean deleteItem(String itemCode){
    return DataLayer.deleteItem(itemCode);
  }

  public static boolean updateItem(String description, int qtyOnHand, double unitPrice, String itemCode){
    return DataLayer.updateItem(new ItemTM(itemCode, description, qtyOnHand, unitPrice));
  }

  public static boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails){
    Connection connection = DBConnection.getInstance().getConnection();
    try {
      connection.setAutoCommit(false);

      boolean result = DataLayer.saveOrder(order);
      if (!result){
        connection.rollback();
        return false;
      }

      result = DataLayer.saveOrderDetail(order.getOrderId(),orderDetails);
      if (!result){
        connection.rollback();
        return false;
      }

      result = DataLayer.updateQty(orderDetails);
      if (!result){
        connection.rollback();
        return false;
      }

      connection.commit();
      return true;

    } catch (SQLException throwables) {
      throwables.printStackTrace();
      try {
        connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return false;
    } finally {
      try {
        connection.setAutoCommit(true);
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }
}
