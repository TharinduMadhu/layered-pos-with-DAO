package dao.custom;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class CrudUtilTest {
    public static void main(String[] args) throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM CUSTOMER WHERE id= ?","C001");
                //executeQuery("SELECT * FROM CUSTOMER WHERE id= ?","C001");




    }



}