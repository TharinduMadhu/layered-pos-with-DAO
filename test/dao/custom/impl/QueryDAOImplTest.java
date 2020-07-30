package dao.custom.impl;


import dao.DAOFactory;
import dao.DAOType;
import dao.SuperDAO;
import dao.custom.QueryDAO;
import db.DBConnection;
import entity.CustomEntity;

class QueryDAOImplTest {

  public static void main(String[] args) {
    QueryDAO queryDAO = DAOFactory.getInstance().getDAO(DAOType.QUERY);
 /*   CustomEntity ce = queryDAO.getOrderDetail("OD001");
    System.out.println(ce.getCustomerName());
    System.out.println(ce.getOrderId());
    System.out.println(ce.getOrderDate());*/

    /*CustomEntity cee = queryDAO.getOrderDetail2("OD001");
    System.out.println(cee.getCustomerId());
    System.out.println(cee.getCustomerName());
    System.out.println(cee.getOrderId());*/

    CustomEntity cee = queryDAO.getOrderDetail3("OD001");
    System.out.println(cee.getOrderId());
    System.out.println(cee.getOrderDate());
    System.out.println(cee.getCustomerId());
    System.out.println(cee.getCustomerName());
    System.out.println(cee.getTotal());



  }


}