package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.AnnualIncomeBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.QueryDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.AnnualIncomeDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.AnnualIncome;

public class AnnualIncomeBoImpl implements AnnualIncomeBo { 
    QueryDAO queryDAO = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Query);

    @Override
    public ObservableList<AnnualIncomeDTO> getAnnualIncome() throws Exception {
        ObservableList<AnnualIncomeDTO>annualincomelist= FXCollections.observableArrayList();
        ObservableList<AnnualIncome> annualIncome = queryDAO.getAnnualIncome();
        for (AnnualIncome income:annualIncome) {
            annualincomelist.add(new AnnualIncomeDTO(income.getNo(),income.getYear(),income.getIncome()));

        }
        return  annualincomelist;
    }
}
