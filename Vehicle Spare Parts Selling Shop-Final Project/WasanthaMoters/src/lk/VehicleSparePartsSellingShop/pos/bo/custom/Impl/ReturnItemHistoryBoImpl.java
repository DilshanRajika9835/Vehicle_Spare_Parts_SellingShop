package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.ReturnItemHistoryBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.ReturnItemHistoryDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.ReturnItemHistoryDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.ReturnItemHistory;

import java.sql.SQLException;


public class ReturnItemHistoryBoImpl  implements ReturnItemHistoryBo {
    ReturnItemHistoryDAO returnItemHistorydao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.ReturnHistory);

    @Override
    public ObservableList<ReturnItemHistoryDTO> GetReturnItems() throws SQLException, ClassNotFoundException {
        ObservableList<ReturnItemHistory> all = returnItemHistorydao.getAll();
        ObservableList<ReturnItemHistoryDTO>returnList= FXCollections.observableArrayList();
        int no=1;
        for (ReturnItemHistory returnitem:all) {
            returnList.add(new ReturnItemHistoryDTO(no++,returnitem.getOrderID(),
                    returnitem.getTotalCost(),returnitem.getReturnQty(),returnitem.getReturnMoney(),
                    returnitem.getModelNo(),returnitem.getReturnDate(),returnitem.getReturnTime()));
        }
        return returnList;
    }

}
