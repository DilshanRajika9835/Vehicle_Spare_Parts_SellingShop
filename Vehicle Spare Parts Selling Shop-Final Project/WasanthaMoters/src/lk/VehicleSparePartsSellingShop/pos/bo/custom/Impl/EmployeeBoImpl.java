package lk.VehicleSparePartsSellingShop.pos.bo.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.VehicleSparePartsSellingShop.pos.bo.EmployeeBo;
import lk.VehicleSparePartsSellingShop.pos.dao.DaoFactory;
import lk.VehicleSparePartsSellingShop.pos.dao.EmployeeDAO;
import lk.VehicleSparePartsSellingShop.pos.dto.EmployeeDTO;
import lk.VehicleSparePartsSellingShop.pos.entity.Employee;

import java.sql.SQLException;

public class EmployeeBoImpl implements EmployeeBo {
    EmployeeDAO dao = DaoFactory.getInstance().getDao(DaoFactory.DaoType.Employee);

    @Override
    public boolean add(EmployeeDTO employee) throws ClassNotFoundException, SQLException {
      return dao.add(new Employee(employee.getNic(),employee.getName(),employee.getAddress(),employee.getGender(),
              employee.getBirthDay(),employee.getPhoneNumber(),employee.getPost(),employee.getSalary()));
    }

    @Override
    public boolean delete(String id) throws ClassNotFoundException, SQLException {
     return  dao.delete(id);
    }

    @Override
    public boolean update(EmployeeDTO update) throws ClassNotFoundException, SQLException {
        return dao.update(new Employee(update.getNic(),update.getName(),update.getAddress(),update.getGender(),
                update.getBirthDay(),update.getPhoneNumber(),update.getPost(),update.getSalary()));
    }

    @Override
    public EmployeeDTO search(String id) throws ClassNotFoundException, SQLException {
        Employee search = dao.search(id);
       return new EmployeeDTO(search.getNicnumber(),search.getName(),search.getAddress(),search.getGender(),
               search.getBirthday(),search.getPhonenumber(),search.getPost(),search.getSalary());
    }

    @Override
    public ObservableList<EmployeeDTO> getAll() throws ClassNotFoundException, SQLException {
        ObservableList<Employee> all = dao.getAll();
        ObservableList<EmployeeDTO> employeelist= FXCollections.observableArrayList();
        for (Employee emp:all) {
            employeelist.add(new EmployeeDTO(emp.getNo(),emp.getNicnumber(),emp.getName(),emp.getAddress(),
                    emp.getGender(),emp.getBirthday(),emp.getPhonenumber(),emp.getPost(),emp.getSalary()));
        }
        return employeelist;
    }
}
