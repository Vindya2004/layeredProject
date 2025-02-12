package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.DeliveryDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Delivery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryDAOImpl implements DeliveryDAO {
    public boolean insert(Delivery deliveryDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Delivery values(?,?,?)",
                deliveryDto.getDeliveryId(),
                deliveryDto.getDeleveryDate(),
                deliveryDto.getDestination()
        );
    }

    public boolean update(Delivery deliveryDto) throws SQLException {
        return SQLUtil.execute(
                "update Delivery set Deli_date=?,Destination=? where Deli_Id=?",
                deliveryDto.getDeleveryDate(),
                deliveryDto.getDestination(),
                deliveryDto.getDeliveryId()
        );
    }

    public boolean delete(Delivery deliveryDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Delivery where Deli_Id"
        );
    }

    public ArrayList<Delivery> loadTbl() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Delivery");


        ArrayList<Delivery> deliveryDtos = new ArrayList<>();

        while (rst.next()) {
            Delivery delivery = new Delivery(
                   rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getDouble(4)

            );
            deliveryDtos.add(delivery);
        }
        return deliveryDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return "";
    }

    //    public String loadNExtID() throws SQLException {
//        ResultSet rst = CrudUtil.execute("select Deli_Id from Delivery order by Deli_Id desc limit 1");
//
//        if (rst.next()) {
//            String lastId = rst.getString(1); // Last customer ID
//            String substring = lastId.substring(1); // Extract the numeric part
//            int i = Integer.parseInt(substring); // Convert the numeric part to integer
//            int newIdIndex = i + 1; // Increment the number by 1
//            return String.format("d%03d", newIdIndex); // Return the new customer ID in format Cnnn
//        }
//        return "d001";
//    }
public String getNewDelId() throws SQLException {
    ResultSet rst = SQLUtil.execute(
            "SELECT Deli_Id from Delivery order by Deli_Id desc limit 1"  //
    );
    while (rst.next()) {
        String lastId = rst.getString(1); // Last customer ID
        String substring = lastId.substring(1); // Extract the numeric part
        int i = Integer.parseInt(substring); // Convert the numeric part to integer
        int newIdIndex = i + 1; // Increment the number by 1
        return String.format("D%03d", newIdIndex); // Return the new customer ID in format Cnnn
    }
    return "D001";
}

    @Override
    public boolean insertDelivery(String deliveryId, String deleveryDate, String destination, double txtDelCharge) throws SQLException {
        return SQLUtil.execute("INSERT INTO Delivery (Deli_Id, Deli_date, Destination, Del_charge) VALUES (?, ?, ?, ?)", deliveryId, deleveryDate, destination, txtDelCharge);

    }
}
