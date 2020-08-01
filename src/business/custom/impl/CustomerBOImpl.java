package business.custom.impl;

import business.custom.CustomerBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.CustomerDAO;
import dao.custom.ItemDAO;
import entity.Customer;
import util.CustomerTM;

import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl  implements CustomerBO  {

    public  String getNewCustomerId()  throws Exception{
            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            String lastCustomerId = customerDAO.getLastCustomerId();
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

    public String getNewItemCode() throws Exception {
            ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
            String lastItemCode = itemDAO.getLastItemCode();
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


    public  List<CustomerTM> getAllCustomers() throws Exception {
            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            List<Customer> allCustomers = customerDAO.findAll();
            List<CustomerTM> customers = new ArrayList<>();
            for (Customer customer : allCustomers) {
                customers.add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress()));
            }
            return customers;
    }

    public  boolean saveCustomer(String id, String name, String address) throws Exception {
            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.save(new Customer(id, name, address));
    }

    public  boolean deleteCustomer(String customerId) throws Exception{

            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.delete(customerId);
    }

    public boolean updateCustomer(String name, String address, String customerId) throws Exception{
            CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);
            return customerDAO.update(new Customer(customerId, name, address));
        }
    }
