package dao.custom;

import dao.SuperDAO;
import entity.CustomEntity;

public interface QueryDAO extends SuperDAO {

  CustomEntity getOrderDetail();
  CustomEntity getOrderDetail2();

}
