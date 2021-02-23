package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.MonthlyIncomeBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.MonthlyIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.MonthlyIncome;

public class MonthlyIncomeBoImpl implements MonthlyIncomeBo {
    QueryDAO queryDAO= DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);

    @Override
    public ObservableList<MonthlyIncomeDTO> getMonthlyIncome() throws Exception {
        ObservableList<MonthlyIncome> all = queryDAO.getMonthlyIncome();
        ObservableList<MonthlyIncomeDTO>monthlyincomelist= FXCollections.observableArrayList();
        for (MonthlyIncome income:all) {
        monthlyincomelist.add(new MonthlyIncomeDTO(income.getNo(),income.getYearName(),income.getMonthName(),
                income.getIncome()));
        }
        return monthlyincomelist;
    }
}
