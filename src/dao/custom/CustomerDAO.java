package dao.custom;

import java.util.List;

import dao.SuperDAO;
import entity.Customer;

public interface CustomerDAO extends SuperDAO<Customer,String > {
  String getLastCustomerId();
}
