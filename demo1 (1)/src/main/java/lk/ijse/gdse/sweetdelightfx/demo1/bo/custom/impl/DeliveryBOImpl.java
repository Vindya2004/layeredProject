package lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.impl;

import lk.ijse.gdse.sweetdelightfx.demo1.Dto.DeliveryDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.EmployeeDto;
import lk.ijse.gdse.sweetdelightfx.demo1.Dto.PaymentDto;
import lk.ijse.gdse.sweetdelightfx.demo1.bo.custom.DeliveryBO;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.DAOFactory;
import lk.ijse.gdse.sweetdelightfx.demo1.dao.custom.DeliveryDAO;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Delivery;
import lk.ijse.gdse.sweetdelightfx.demo1.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class DeliveryBOImpl implements DeliveryBO {
    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.DELIVERY);


    @Override
    public boolean insert(DeliveryDto customerDto) throws SQLException {
        return deliveryDAO.insert(new Delivery(customerDto.getDeliveryId(),customerDto.getDeleveryDate(),customerDto.getDestination(),customerDto.getTxtDelCharge()));
    }

    @Override
    public boolean update(DeliveryDto customerDto) throws SQLException {
        return deliveryDAO.update(new Delivery(customerDto.getDeliveryId(),customerDto.getDeleveryDate(),customerDto.getDestination(),customerDto.getTxtDelCharge()));
    }

    @Override
    public boolean delete(Delivery customerDto) throws SQLException {
        return deliveryDAO.delete(customerDto);
    }

    @Override
    public ArrayList<DeliveryDto> loadTbl() throws SQLException {
        ArrayList<Delivery> load = deliveryDAO.loadTbl();
        ArrayList<DeliveryDto> deliveryDtos = new ArrayList<>();
        for (Delivery delivery : load) {
            deliveryDtos.add(new DeliveryDto(delivery.getDeliveryId(),delivery.getDeleveryDate(),delivery.getDestination(),delivery.getTxtDelCharge()));
        }
        return deliveryDtos;
    }

    @Override
    public String loadNExtID() throws SQLException {
        return "";
    }

    @Override
    public String getNewDelId() throws SQLException {
        return deliveryDAO.getNewDelId();
    }
}
