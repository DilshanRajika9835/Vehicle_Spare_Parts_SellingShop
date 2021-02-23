package lk.VehicleSparePartsSellingShop.pos.dao;

import lk.VehicleSparePartsSellingShop.pos.db.DBConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CrudUtil {
    public static <T>T execute(String sql,Object... param) throws SQLException, ClassNotFoundException {

        PreparedStatement preparedStatement = DBConnection.getInstance().getConnection().prepareStatement(sql);

            for (int i = 0; i <param.length ; i++) {
                preparedStatement.setObject((i+1),param[i]);
            }
            if (sql.startsWith("SELECT")){
                return (T)preparedStatement.executeQuery();

            }

        return ((T)(Boolean)(preparedStatement.executeUpdate()>0));

    }
}
