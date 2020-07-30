package dao;

import java.io.Serializable;
import java.util.List;

public interface CrudDAO <T extends Serializable,ID extends Serializable> extends SuperDAO{

  List<T> findAll();
  T find(ID key);
  boolean save(T entity);
  boolean update(T entity);
  boolean delete(ID key);
}
