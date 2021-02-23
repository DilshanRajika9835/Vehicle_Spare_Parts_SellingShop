package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.LoginDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Login;
import lk.VehicleSparePartsSellingShop.pos.entity.LoginHistory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    @Override
    public boolean add(LoginHistory loginHistory) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean delete(String s) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public boolean update(LoginHistory loginHistory) throws ClassNotFoundException, SQLException {
        return false;
    }

    @Override
    public LoginHistory search(String s) throws ClassNotFoundException, SQLException {
        return null;
    }

    @Override
    public ObservableList<LoginHistory> getAll() throws SQLException, ClassNotFoundException {
      ObservableList<LoginHistory>loginlist= FXCollections.observableArrayList();

            ResultSet rst  = CrudUtil.execute("SELECT * FROM Login ORDER BY LoginID DESC");
            while (rst.next()){
                loginlist.add(new LoginHistory(rst.getInt(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));

            }

    return loginlist;

    }

    @Override
    public String getNICNumber(String name) throws SQLException, ClassNotFoundException {

            ResultSet rst = CrudUtil.execute("SELECT  NICNumber FROM Employee WHERE Name=? ",name);
            if(rst.next()){
            return rst.getString("NICNumber");
            }

        return "Notfound";
    }

    @Override
    public void AddLoginDetail(Login login) throws SQLException, ClassNotFoundException {

            CrudUtil.execute("INSERT INTO Login(UserName,PassWord,LoginDate,LoginTime,LoginStates) VALUES(?,?,?,?,?)",login.getUserName(),login.getPassword(),login.getLoginDate(),login.getLoginTime(),login.getLoginStates() );


    }




}
