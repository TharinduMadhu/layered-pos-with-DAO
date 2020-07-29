package business;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.impl.CustomerDAOImpl;
import dao.impl.ItemDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailDAOImpl;
import db.DBConnection;
import entity.Customer;
import entity.Item;
import entity.Order;
import entity.OrderDetail;
import util.CustomerTM;
import util.ItemTM;
import util.OrderDetailTM;
import util.OrderTM;

public class BusinessLayer {

  public static String getNewCustomerId() {
    String lastCustomerId = CustomerDAOImpl.getLastCustomerId();
    if (lastCustomerId == null) {
      return "C001";
    } else {
      int maxId = Integer.parseInt(lastCustomerId.replace("C", ""));
      maxId = maxId + 1;
      String id = "";
      if (maxId < 10) {
        id = "C00" + maxId;
      } else if (maxId < 100) {
        id = "C0" + maxId;
      } else {
        id = "C" + maxId;
      }
      return id;
    }
  }

  public static String getNewItemCode() {
    String lastItemCode = ItemDAOImpl.getLastItemCode();
    if (lastItemCode == null) {
      return "I001";
    } else {
      int maxId = Integer.parseInt(lastItemCode.replace("I", ""));
      maxId = maxId + 1;
      String id = "";
      if (maxId < 10) {
        id = "I00" + maxId;
      } else if (maxId < 100) {
        id = "I0" + maxId;
      } else {
        id = "I" + maxId;
      }
      return id;
    }
  }

  public static String getNewOrderId() {
    String lastOrderId = OrderDAOImpl.getLastOrderId();
    if (lastOrderId == null) {
      return "OD001";
    } else {
      int maxId = Integer.parseInt(lastOrderId.replace("OD", ""));
      maxId = maxId + 1;
      String id = "";
      if (maxId < 10) {
        id = "OD00" + maxId;
      } else if (maxId < 100) {
        id = "OD0" + maxId;
      } else {
        id = "OD" + maxId;
      }
      return id;
    }
  }

  public static List<CustomerTM> getAllCustomers() {
    List<Customer> allCustomers = CustomerDAOImpl.findAllCustomers();
    List<CustomerTM> customers = new ArrayList<>();
    for (Customer customer : allCustomers) {
      customers.add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress()));
    }
    return customers;
  }

  public static boolean saveCustomer(String id, String name, String address) {
    return CustomerDAOImpl.saveCustomer(new Customer(id, name, address));
  }

  public static boolean deleteCustomer(String customerId) {
    return CustomerDAOImpl.deleteCustomer(customerId);
  }

  public static boolean updateCustomer(String name, String address, String customerId) {
    return CustomerDAOImpl.updateCustomer(new Customer(customerId, name, address));
  }

  public static List<ItemTM> getAllItems() {
    List<Item> allItems = ItemDAOImpl.findAllItems();
    List<ItemTM> items = new ArrayList<>();
    for (Item item : allItems) {
      items.add(new ItemTM(item.getCode(), item.getDescription(), item.getQtyOnHand(),
          item.getUnitPrice().doubleValue()));
    }
    return items;
  }

  public static boolean saveItem(String code, String description, int qtyOnHand, double unitPrice) {
    return ItemDAOImpl.saveItem(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
  }

  public static boolean deleteItem(String itemCode) {
    return ItemDAOImpl.deleteItem(itemCode);
  }

  public static boolean updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) {
    return ItemDAOImpl.updateItem(new Item(itemCode, description,
        BigDecimal.valueOf(unitPrice), qtyOnHand));
  }


  public static boolean placeOrder(OrderTM order, List<OrderDetailTM> orderDetails) {
    Connection connection = DBConnection.getInstance().getConnection();
    try {
      connection.setAutoCommit(false);
      boolean result = OrderDAOImpl.saveOrder(new Order(order.getOrderId(),
          Date.valueOf(order.getOrderDate()),
          order.getCustomerId()));
      if (!result) {
        connection.rollback();
        return false;
      }
      for (OrderDetailTM orderDetail : orderDetails) {
        result = OrderDetailDAOImpl.saveOrderDetail(new OrderDetail(
            order.getOrderId(), orderDetail.getCode(),
            orderDetail.getQty(), BigDecimal.valueOf(orderDetail.getUnitPrice())
        ));
        if (!result){
          connection.rollback();
          return false;
        }
        Item item = ItemDAOImpl.findItem(orderDetail.getCode());
        item.setQtyOnHand(item.getQtyOnHand() - orderDetail.getQty());
        result = ItemDAOImpl.updateItem(item);
        if (!result){
          connection.rollback();
          return false;
        }
      }
      connection.commit();
      return true;
    } catch (Throwable throwables) {
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
