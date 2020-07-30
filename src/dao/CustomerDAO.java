package dao;

import java.util.List;

import entity.Customer;

public interface CustomerDAO extends SuperDAO<Customer,String >{
  String getLastCustomerId();
}
