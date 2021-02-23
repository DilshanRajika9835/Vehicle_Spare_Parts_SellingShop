package lk.VehicleSparePartsSellingShop.pos.dao.custom.Impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.VehicleSparePartsSellingShop.pos.dao.CrudUtil;
import lk.VehicleSparePartsSellingShop.pos.dao.SupplierDAO;
import lk.VehicleSparePartsSellingShop.pos.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {

            String supplyid= supplier.getSupplierID();
            String companyname=supplier.getCompany();
            String name=supplier.getName();
            String tel=supplier.getPhoneNumber();
            String companyaddress=supplier.getCompanyAddress();
            String email=supplier.getCompanyEmailAddress();
            return CrudUtil.execute("INSERT INTO Supplier VALUES(?,?,?,?,?,?)",
                    supplyid, companyname, name, tel, companyaddress,email);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {

            return CrudUtil.execute("DELETE FROM Supplier WHERE SupplierID=?", id);

    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        String supplyid= supplier.getSupplierID();
        String companyname=supplier.getCompany();
        String name=supplier.getName();
        String tel=supplier.getPhoneNumber();
        String companyaddress=supplier.getCompanyAddress();
        String email=supplier.getCompanyEmailAddress();

            return CrudUtil.execute("UPDATE Supplier SET CompanyName=?,SupplierName=?,Tel=?,CompanyAddress=?,CompanyEmailAddress=? WHERE SupplierID=?", companyname, name, tel, companyaddress,email,supplyid);

    }

    @Override
    public Supplier search(String id) throws ClassNotFoundException, SQLException {
        ResultSet rst = CrudUtil.execute("SELECT * FROM Supplier WHERE SupplierID=?", id);
        if(rst.next()){
            return new Supplier(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),rst.getString(5),
                    rst.getString(6));
        }
        return null;
    }

    @Override
    public ObservableList<Supplier> getAll() throws ClassNotFoundException, SQLException {
        ObservableList<Supplier>supplierlist= FXCollections.observableArrayList();
        ResultSet rst = CrudUtil.execute("SELECT * FROM Supplier");
        int No=1;
        while (rst.next()){
            supplierlist.add(new Supplier(No++,rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),rst.getString(5),
                    rst.getString(6)));
        }
       return supplierlist;
    }


}
