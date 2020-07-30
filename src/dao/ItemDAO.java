package dao;

import java.util.List;

import entity.Item;

public interface ItemDAO extends SuperDAO{
  String getLastItemCode();
}
