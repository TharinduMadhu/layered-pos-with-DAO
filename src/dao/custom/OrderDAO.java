package dao.custom;

import java.util.List;

import dao.SuperDAO;
import entity.Order;

public interface OrderDAO extends SuperDAO<Order,String> {
  String getLastOrderId();

}
