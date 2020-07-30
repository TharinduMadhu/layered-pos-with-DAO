package dao.custom;

import java.util.List;

import dao.SuperDAO;
import entity.CustomEntity;

public interface QueryDAO extends SuperDAO {

  CustomEntity getOrderDetail(String orderId);
  CustomEntity getOrderDetail2(String orderId);
  CustomEntity getOrderDetail3(String orderId);
  List<CustomEntity> getOrderDetail4(String orderId);

}
