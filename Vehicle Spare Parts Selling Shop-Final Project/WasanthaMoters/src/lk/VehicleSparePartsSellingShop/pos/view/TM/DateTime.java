package lk.VehicleSparePartsSellingShop.pos.view.TM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTime {
    public static String getDateTime(String model){
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat(model);
        String format = sdf.format(date);

        return format;
    }
}
