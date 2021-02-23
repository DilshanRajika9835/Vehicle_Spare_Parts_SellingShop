package lk.VehicleSparePartsSellingShop.pos.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lk.VehicleSparePartsSellingShop.pos.bo.*;
import lk.VehicleSparePartsSellingShop.pos.db.DBConnection;
import lk.VehicleSparePartsSellingShop.pos.dto.ItemDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.OrderDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.OrderDetailDTO;
import lk.VehicleSparePartsSellingShop.pos.dto.PaymentDTO;
import lk.VehicleSparePartsSellingShop.pos.view.Notification.Notification;
import lk.VehicleSparePartsSellingShop.pos.view.TM.DateTime;
import lk.VehicleSparePartsSellingShop.pos.view.TM.SelectItemTM;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.regex.Pattern;

import static net.sf.jasperreports.engine.JasperFillManager.fillReport;

public class OrdersFormController {

    public TableView<SelectItemTM> tblselectitem;
    public TableColumn colmodelno;
    public TableColumn coldescription;
    public TableColumn coltype;
    public TableColumn colunitprice;
    public TableColumn coldiscount;
    public TableColumn colqty;
    public TableColumn coltotal;
    public ComboBox cmbmodelnumber;
    public TableColumn colremove;
    public JFXTextField txtorderid;
    public JFXTextField txtmodelno;
    public JFXTextField txtqtyonhand;
    public JFXTextField txtunitprice;
    public JFXTextField txtbrand;
    public JFXTextField txtcolor;
    public JFXTextField txttype;
    public JFXTextField txtwidth;
    public JFXTextField txtdiscount;
    public JFXTextField txtdescription;
    public JFXTextField txtheight;
    public JFXTextField txtqty;
    public JFXTextField txttotalprice;
    public JFXTextField txtcash;
    public JFXTextField txtbalance;
    public JFXTextField txtdiscountprice;


    OrderBo orderbo = BoFactory.getInstance().getBo(BoFactory.BoType.Order);

    ObservableList<SelectItemTM>itemlist=FXCollections.observableArrayList();
    public void initialize()  {
      ;
        GenarateOrderID();
        colmodelno.setCellValueFactory(new PropertyValueFactory<>("ModelNo"));
        coldescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("Type"));
        colunitprice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        coldiscount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        colqty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        coltotal.setCellValueFactory(new PropertyValueFactory<>("Total"));
        colremove.setCellValueFactory(new PropertyValueFactory<>("btn"));
        LoadModelNO();
        tblselectitem.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
                if(newValue!=null){
                    LoadUniqueData(newValue);

                }
        }));


    }

    private void GenarateOrderID() {

        try {
            String orderID = orderbo.getOrderID();
            txtorderid.setText(orderID);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void LoadModelNO() {
        try {
            ObservableList modellist=FXCollections.observableArrayList();
            ArrayList<ItemDTO> modelNo = orderbo.getModelNo();
            for (ItemDTO dto:modelNo) {
                modellist.add(dto.getModelNo());
            }
            cmbmodelnumber.setItems(modellist);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void LoadUniqueData(SelectItemTM newvalue){

        try {
            ItemDTO search  = orderbo.Search(newvalue.getModelNo());
            txtmodelno.setText(newvalue.getModelNo());
            cmbmodelnumber.setValue(newvalue.getModelNo());
            txttype.setText(newvalue.getType());
            txtdescription.setText(newvalue.getDescription());
            txtqtyonhand.setText(Integer.toString(search.getQtyOnHand()));
            txtbrand.setText(search.getBrand());
            txtwidth.setText(search.getWidth());
            txtheight.setText(search.getHeight());
            txtunitprice.setText(Double.toString(newvalue.getUnitPrice()));
            txtcolor.setText(search.getColor());
            txtdiscount.setText(Double.toString(search.getDiscount()));
            txtqty.setText(Integer.toString(newvalue.getQty()));
            CheckQtyStock();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



}

  public  void Calculate(){
      txtbalance.setText(null);
      txtcash.setText(null);
       double total=0.00;
       double discount=0.00;
      for (int i = 0; i < tblselectitem.getItems().size() ; i++) {
          discount+=(Math.round(Double.parseDouble( coldiscount.getCellData(i).toString())));
          total +=Math.round(Double.parseDouble( coltotal.getCellData(i).toString()));
      }

    txttotalprice.setText(Double.toString(Math.round(Math.round(total))));
      txtdiscountprice.setText(Double.toString(Math.round(Math.round(discount))));


  }
  public int AllreadyExists(String modelNo){

      for (int i = 0; i <tblselectitem.getItems().size(); i++) {
          if(colmodelno.getCellData(i).equals(modelNo)){
              return i;
          }

      }
    return -1;
  }

    public void btnSelectItemOnAction(ActionEvent actionEvent) {
        try {
            int qtyOnHand = Integer.parseInt(txtqtyonhand.getText());
            int Qty = Integer.parseInt(txtqty.getText());
            if (Pattern.compile("^[-]{1}[0-9]|[0-9]{1,}$").matcher(txtqty.getText()).matches() && qtyOnHand >= Qty) {
                selectItemList();
                Calculate();
                txtcash.requestFocus();

            }else if(Integer.parseInt(txtqty.getText())<0&&Integer.parseInt(txtqtyonhand.getText())==0){
                if(AllreadyExists(txtmodelno.getText())>0){
                    selectItemList();
                    Calculate();
                    txtcash.requestFocus();
                }
            }
        }catch (Exception ex){
            if(txtmodelno.getText().length()>0) {
                txtqty.setFocusColor(Paint.valueOf("red"));
                txtqty.requestFocus();
            }else {
                txtmodelno.setFocusColor(Paint.valueOf("red"));
                txtmodelno.requestFocus();
            }

        }

    }
public  void selectItemList(){
            String modelno=txtmodelno.getText();
            String description=txtdescription.getText();
            String type=txttype.getText();
            Button btn=new Button("Remove");
            btn.setMaxSize(120,25);
            double unitprice=Double.parseDouble(txtunitprice.getText());
            int qty=Integer.parseInt(txtqty.getText());
            double itemdiscount=Double.parseDouble(txtdiscount.getText());
            double discount= Math.round((qty*unitprice)*(itemdiscount/100));
            double total=Math.round((qty*unitprice)-discount);
            btn.setOnAction((e) ->{
                SelectItemTM selecteditem = tblselectitem.getSelectionModel().getSelectedItem();
                tblselectitem.getItems().remove(selecteditem);
                ClearData();
                Calculate();
            });
            int index=AllreadyExists(txtmodelno.getText());
            if(index<0){
                if(Integer.parseInt(txtqty.getText())>0){
                    itemlist.add(new SelectItemTM(modelno, description, type, unitprice, discount, qty, total, btn));
                    tblselectitem.setItems(itemlist);
                    CheckQtyStock();
                    Calculate();

                }else {
                    txtqty.setFocusColor(Paint.valueOf("red"));
                    txtqty.requestFocus();
                }

            }else{
                    SelectItemTM selectItemTM = itemlist.get(index);
                    if(ChangeItemQty(index)){
                    int orderqty = selectItemTM.getQty() + Integer.parseInt(txtqty.getText());
                    double desc = Math.round((orderqty * unitprice) * (itemdiscount / 100));
                    double totalcost = Math.round(orderqty * Double.parseDouble(txtunitprice.getText()) - desc);
                    itemlist.add(new SelectItemTM(modelno, description, type, unitprice, desc, orderqty, totalcost, btn));
                    tblselectitem.setItems(itemlist);
                    tblselectitem.getItems().remove(index);
                    CheckQtyStock();
                    Calculate();
                    }else {
                        txtqty.setFocusColor(Paint.valueOf("red"));
                        txtqty.requestFocus();
                    }
            }

            txtcash.requestFocus();

}
public   boolean ChangeItemQty(int index){
    SelectItemTM selectItemTM = itemlist.get(index);
    int rQty=Integer.parseInt(txtqty.getText());
     int tQty=selectItemTM.getQty();
     if(rQty<0){
     int different=tQty+(rQty);
     if(different>0){
         return  true;
     }else if(different <= 0){
            int upQty= Integer.parseInt(txtqtyonhand.getText())+tQty;
            txtqtyonhand.setText(Integer.toString(upQty));
             itemlist.remove(index);
         }

     }else if (rQty > 0) {
             return true;
         }

  return false;
}

    public void btnConformOrderOnAction(ActionEvent actionEvent) {
        try{
        if(Pattern.compile("^[0-9]{2,}$").matcher(txtcash.getText()).matches()){
            ButtonType yes=new ButtonType("Yes");
            ButtonType no=new ButtonType("No");
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure Conform this Order?",yes,no);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if(buttonType.orElse(no)==yes){

                    if(txttotalprice.getText().length()>0&&txtcash.getText().length()>0&&txtbalance.getText().length()>0){
                        ArrayList<OrderDetailDTO>orderdetail=new ArrayList<>();
                        String Date= DateTime.getDateTime("yyyy/MM/dd");
                        String Time=DateTime.getDateTime("HH:mm:ss a");
                        String OrderID=txtorderid.getText();
                        for (int i = 0; i <tblselectitem.getItems().size(); i++) {
                            String ModelNo= colmodelno.getCellData(i).toString();
                            int OrderQty=Integer.parseInt(colqty.getCellData(i).toString());
                            double Descount=Math.round(Double.parseDouble(coldiscount.getCellData(i).toString()));
                            orderdetail.add(new OrderDetailDTO(OrderID,ModelNo,OrderQty,Descount));
                        }
                        PaymentDTO paymentDTO=new PaymentDTO(txtorderid.getText(),"Cash",
                                Double.parseDouble(txttotalprice.getText()),Double.parseDouble(txtcash.getText()),
                                Double.parseDouble(txtbalance.getText()));
                        OrderDTO order = new OrderDTO(OrderID,Date,Time,orderdetail,paymentDTO);

                        boolean added = orderbo.placeOrder(order);
                        if(added){
                            if(added){
                               Genaratereport();
                                Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/successfully.png",
                                        "Order Place Successfully!","Successfully!");
                                ClearData();
                                ClearCalculation();
                                tblselectitem.getItems().clear();
                                initialize();
                            }
                        }
                    }else{
                        Notification.getNotification("/lk/VehicleSparePartsSellingShop/pos/assert/warning.png",
                                "Please Enter The payment!","Warning!");

                    }
            }
        }else{
            txtcash.setFocusColor(Paint.valueOf("red"));
            txtcash.requestFocus();
        }
        }catch (Exception ex){
            txtcash.setFocusColor(Paint.valueOf("red"));
            txtcash.requestFocus();
        }


    }

    private void Genaratereport() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("OrderID",txtorderid.getText());
        hm.put("total",Double.parseDouble(txttotalprice.getText()));
        hm.put("balance",Double.parseDouble(txtbalance.getText()));
        hm.put("saving",Double.parseDouble(txtdiscountprice.getText()));
        hm.put("regno",txtorderid.getText());
        hm.put("cash",Double.parseDouble(txtcash.getText()));

        try {
            //JasperDesign is= JRXmlLoDilshan Rajikaader.load("C:\\Users\\DELL\\untitled\\src\\Thogakade\\lk.VehicleSparePartsSellingShop.pos.report/CustomerBill.jrxml");
            InputStream is=this.getClass().getResourceAsStream("/lk/VehicleSparePartsSellingShop/pos/report/custombill.jasper");
            //JasperReport jr = JasperCompileManager.compileReport(is);
            JasperPrint jp= JasperFillManager.fillReport(is,hm, DBConnection.getInstance().getConnection());
            //JasperPrintManager.printReport(jp,true);
            JasperViewer.viewReport(jp,false);
        } catch (JRException ex) {
            new Alert(Alert.AlertType.CONFIRMATION,ex.getMessage(),ButtonType.CLOSE).show();
        } catch (SQLException ex) {
            new Alert(Alert.AlertType.CONFIRMATION,ex.getMessage(),ButtonType.CLOSE).show();
        } catch (ClassNotFoundException ex) {
            new Alert(Alert.AlertType.CONFIRMATION,ex.getMessage(),ButtonType.CLOSE).show();
        }
    }

    private  void ClearCalculation(){
        txttotalprice.setText(null);
        txtcash.setText(null);
        txtbalance.setText(null);
        txtdiscountprice.setText(null);
    }
    private void ClearData() {

        txtmodelno.setText(null);
        cmbmodelnumber.setValue(null);
        txttype.setText(null);
        txtdescription.setText(null);
        txtqtyonhand.setText(null);
        txtbrand.setText(null);
        txtwidth.setText(null);
        txtheight.setText(null);
        txtunitprice.setText(null);
        txtcolor.setText(null);
        txtdiscount.setText(null);
        txtqty.setText(null);


    }

    public void CmbSelectonAction(ActionEvent actionEvent) {

        try {
            txtmodelno.setText(cmbmodelnumber.getValue().toString());
            txtModelNoSearchOnAction(actionEvent);
            txtqty.setText(null);

        } catch (NullPointerException ex) {
        }

    }


    public void txtModelNoSearchOnAction(ActionEvent actionEvent) {

        try {
            ItemDTO search =  orderbo.Search(txtmodelno.getText());
            txtmodelno.setText(search.getModelNo());
            cmbmodelnumber.setValue(search.getModelNo());
            txttype.setText(search.getType());
            txtdescription.setText(search.getDescription());
            txtqtyonhand.setText(Integer.toString(search.getQtyOnHand()));
            txtbrand.setText(search.getBrand());
            txtwidth.setText(search.getWidth());
            txtheight.setText(search.getHeight());
            txtunitprice.setText(Double.toString(search.getUnitPrice()));
            txtcolor.setText(search.getColor());
            txtdiscount.setText(Double.toString(search.getDiscount()));
            CheckQtyStock();
            txtqty.requestFocus();
            txtqty.setFocusColor(Paint.valueOf("blue"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public void txtItemSelectOnAction(ActionEvent actionEvent) {
        try{btnSelectItemOnAction(actionEvent);}
        catch (Exception ex){
           if(txtdescription.getText().length()>0){
               txtqty.requestFocus();
               txtqty.setFocusColor(Paint.valueOf("red"));
           } else {
               txtmodelno.requestFocus();
               txtmodelno.setFocusColor(Paint.valueOf("red"));
           }
        }
    }

    public void txtCashPaymentOnAction(ActionEvent actionEvent) {
        try {
        if(Pattern.compile("^[0-9]{1,}|[.]$").matcher(txtcash.getText()).matches()){
                double total =Math.round( Double.parseDouble(txttotalprice.getText()));
                double cash =Math.round( Double.parseDouble(txtcash.getText()));
                if (total <= cash) {
                    double balance =Math.round((cash - total));
                    txtbalance.setText(Double.toString(balance));
                    txtcash.setFocusColor(Paint.valueOf("blue"));
                }else{
                    txtcash.setFocusColor(Paint.valueOf("red"));
                    txtcash.requestFocus();
                }
        }else {
            txtcash.setFocusColor(Paint.valueOf("red"));
            txtcash.requestFocus();
        }
        }catch(Exception ex){
            txtcash.setFocusColor(Paint.valueOf("red"));
            txtcash.requestFocus();
        }

    }
    public  void CheckQtyStock(){
        try {
            String modelno = txtmodelno.getText();
            for (int y = 0; y < tblselectitem.getItems().size(); y++) {
                if (colmodelno.getCellData(y).equals(modelno)) {
                    SelectItemTM selectItemTM = itemlist.get(y);
                    ItemDTO search = orderbo.Search(modelno);
                    int qty = selectItemTM.getQty();
                    int qtyOnHand = Integer.parseInt(search.getQtyOnHand() + "");
                    txtqtyonhand.setText(Integer.toString(qtyOnHand - qty));
                }

            }
        }catch (Exception ex){

        }
    }


    public void lblClearClickOnAction(MouseEvent mouseEvent) {
        ClearData();
    }
}
