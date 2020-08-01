package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T extends Serializable,ID extends Serializable> extends SuperDAO{

  List<T> findAll() throws Exception;
  T find(ID key) throws Exception;
  boolean save(T entity) throws Exception;
  boolean update(T entity) throws Exception;
  boolean delete(ID key) throws Exception;


}
