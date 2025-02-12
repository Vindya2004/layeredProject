package lk.ijse.gdse.sweetdelightfx.demo1.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class DeliveryDto {
    private String deliveryId;
    private String deleveryDate;
    private String destination;
    private double txtDelCharge;


}
