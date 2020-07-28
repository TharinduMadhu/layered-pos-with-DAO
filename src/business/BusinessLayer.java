package business;

import java.util.List;

import dao.DataLayer;
import util.CustomerTM;

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

}
