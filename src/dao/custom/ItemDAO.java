package dao.custom;

import java.util.List;

import dao.CrudDAO;
import dao.SuperDAO;
import entity.Item;

public interface ItemDAO extends CrudDAO<Item,String> {
  String getLastItemCode() throws Exception;
}
