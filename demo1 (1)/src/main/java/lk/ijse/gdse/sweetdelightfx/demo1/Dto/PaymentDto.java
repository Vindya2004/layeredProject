package lk.ijse.gdse.sweetdelightfx.demo1.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class PaymentDto {
    private String paymentId;
    private String paymentMethod;
    private String paymentDate;
    private double cost;
    private String OrdId;





}
