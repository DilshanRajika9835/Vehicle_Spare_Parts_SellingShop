package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.LoginBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.LoginDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.LoginDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.LoginHistoryDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Login;
import lk.VehicleSparePartsSellingShop.pos.entity.LoginHistory;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoginBoImpl implements LoginBo {
    LoginDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.LoginHistory);

    @Override
    public ObservableList<LoginHistoryDTO> getLoginHistory() throws SQLException, ClassNotFoundException {
        ObservableList<LoginHistory> loginHistory = dao.getAll();
        ObservableList<LoginHistoryDTO>loginlist= FXCollections.observableArrayList();
        for (LoginHistory login:loginHistory) {
        loginlist.add(new LoginHistoryDTO(login.getLoginID(),login.getUserName(),login.getPassword(),
                login.getLoginDate(),login.getLoginTime(),login.getLoginStates()));
        }
        return loginlist;
    }


    @Override
    public String CheckID(String name) throws SQLException, ClassNotFoundException {
      return dao.getNICNumber(name);
    }

    @Override
    public void addLoginHistory(LoginDTO login) throws SQLException, ClassNotFoundException {
        dao.AddLoginDetail(new Login(login.getUserName(),login.getPassword(),login.getLoginDate(),
                login.getLoginTime(),login.getLoginStates()));
    }



}
