package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.custom.DailyIncomeBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.DailyIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.DailyIncome;

import java.sql.SQLException;

public class DailyIncomeBoImpl implements DailyIncomeBo {
    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);
    @Override
    public ObservableList<DailyIncomeDTO> dailyIncome() throws SQLException, ClassNotFoundException {
        ObservableList<DailyIncome> all = queryDAO.getDailyIncome();
        ObservableList<DailyIncomeDTO>dailyincomelist= FXCollections.observableArrayList();
        for (DailyIncome dailyincome:all) {
            dailyincomelist.add(new DailyIncomeDTO(dailyincome.getNo(),dailyincome.getYear(),dailyincome.getMonthname(),
                    dailyincome.getWeekName(),dailyincome.getDay(),dailyincome.getIncome()));

        }
        return dailyincomelist;

    }
}
