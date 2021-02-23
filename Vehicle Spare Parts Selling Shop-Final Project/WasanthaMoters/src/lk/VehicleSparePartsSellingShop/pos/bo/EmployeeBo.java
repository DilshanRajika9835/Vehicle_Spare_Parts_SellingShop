package lk.VehicleSparePartsSellingShop.pos.bo;

import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.dto.EmployeeDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Employee;

import java.sql.SQLException;

public interface EmployeeBo extends SuperBo {
    public boolean add(EmployeeDTO employee)throws ClassNotFoundException, SQLException;
    public boolean delete(String id)throws ClassNotFoundException, SQLException;
    public boolean update(EmployeeDTO update)throws ClassNotFoundException, SQLException;
    public EmployeeDTO search(String id)throws ClassNotFoundException, SQLException;
    public ObservableList<EmployeeDTO> getAll()throws ClassNotFoundException, SQLException;
}
