package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.EmployeeDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Employee;


import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute("INSERT INTO Employee VALUES(?,?,?,?,?,?,?,?)",
                    employee.getNicnumber(), employee.getName(),employee.getAddress(),employee.getGender(),
                    employee.getBirthday(),employee.getPhonenumber(),employee.getPost(),employee.getSalary());
    }

    @Override
    public boolean delete(String NICNumber) throws SQLException, ClassNotFoundException {
            return CrudUtil.execute("DELETE FROM Employee WHERE NICNumber=?",NICNumber);

    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {

           return CrudUtil.execute("UPDATE Employee SET Name=?,Address=?,Gender=?,BirthDay=?,PhoneNumber=?,Post=?,Salary=? WHERE NICNumber=? ",
                   employee.getName(),employee.getAddress(),employee.getGender(),employee.getBirthday(),
                   employee.getPhonenumber(),employee.getPost(),employee.getSalary(),employee.getNicnumber());

    }

    @Override
    public Employee search(String NICNumber) throws SQLException, ClassNotFoundException {
            ResultSet rst = CrudUtil.execute("SELECT * FROM Employee WHERE NICNumber=?", NICNumber);
            if(rst.next()){
             return new Employee(rst.getString(1),rst.getString(2),
                     rst.getString(3),rst.getString(4),rst.getString(5),
                     rst.getString(6),rst.getString(7),rst.getDouble(8));
            }
        return null;
    }

    @Override
    public ObservableList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ObservableList <Employee>employeelist= FXCollections.observableArrayList();
            ResultSet rst = CrudUtil.execute("SELECT * FROM Employee");
            int No=1;
            while (rst.next()){
             employeelist.add(new Employee(No++,rst.getString(1),
                     rst.getString(2),rst.getString(3),rst.getString(4),
                     rst.getString(5),rst.getString(6),rst.getString(7),
                     rst.getDouble(8)));
            }

        return employeelist;

    }


}
