package dao.custom;

import java.util.List;

import dao.SuperDAO;
import entity.Item;

public interface ItemDAO extends SuperDAO<Item,String> {
  String getLastItemCode();
}
