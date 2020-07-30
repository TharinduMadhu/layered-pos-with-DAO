package dao;

import java.util.List;

import entity.Order;

public interface OrderDAO extends SuperDAO<Order,String>{
  String getLastOrderId();

}
