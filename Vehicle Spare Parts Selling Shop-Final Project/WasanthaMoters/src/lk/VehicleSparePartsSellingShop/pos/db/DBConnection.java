package lk.VehicleSparePartsSellingShop.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection dbConnection;
    private  Connection connection;

    private  DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/RajikaMoters","root","ijse");
    }
    public Connection getConnection(){
        return  connection;

    }
    public static DBConnection getInstance() throws SQLException, ClassNotFoundException {
     if(null==dbConnection){
         dbConnection=new DBConnection();

     }
     return dbConnection;

    }
}
