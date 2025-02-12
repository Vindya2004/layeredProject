package lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.impl;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.InventoryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class InventoryDAOImpl {
    public boolean insertInventory(InventoryDto inventoryDto) throws SQLException {
        return SQLUtil.execute(
                "Insert into Inventory values(?,?)",
                inventoryDto.getInventoryId(),
                inventoryDto.getQuantity()
        );
    }

    public boolean updateInventory(InventoryDto inventoryDto) throws SQLException {
        return SQLUtil.execute(
                "update Inventory set qty=? where Inv_Id=?",
                inventoryDto.getQuantity(),
                inventoryDto.getInventoryId()
        );
    }

    public boolean deleteInventory(InventoryDto inventoryDto) throws SQLException {
        return SQLUtil.execute(
                "delete from Inventory where Inv_Id=?",
                inventoryDto.getInventoryId()
        );
    }

    public ArrayList<InventoryDto> loadTblinve() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Inventory");

        ArrayList<InventoryDto> inventoryDtos = new ArrayList<>();

        while (rst.next()) {
            InventoryDto inventoryDto = new InventoryDto(
                    rst.getString(1),
                    rst.getInt(2)
            );
            inventoryDtos.add(inventoryDto);
        }
        return inventoryDtos;
    }
}
