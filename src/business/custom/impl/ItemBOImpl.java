package business.custom.impl;

import business.custom.ItemBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.ItemDAO;
import entity.Item;
import util.ItemTM;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {

    public  List<ItemTM> getAllItems()  throws Exception{
        ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
        List<Item> allItems = null;
        try {
            allItems = itemDAO.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<ItemTM> items = new ArrayList<>();
        for (Item item : allItems) {
            items.add(new ItemTM(item.getCode(), item.getDescription(), item.getQtyOnHand(),
                    item.getUnitPrice().doubleValue()));
        }
        return items;
    }

    public boolean saveItem(String code, String description, int qtyOnHand, double unitPrice) throws Exception {
        ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
            return itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));
    }

    public  boolean deleteItem(String itemCode) throws Exception {
        ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
            return itemDAO.delete(itemCode);
    }

    public  boolean updateItem(String description, int qtyOnHand, double unitPrice, String itemCode) throws Exception {
        ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
            return itemDAO.update(new Item(itemCode, description,
                    BigDecimal.valueOf(unitPrice), qtyOnHand));
        }
    }
