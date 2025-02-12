package lk.ijse.gdse.sweetdelightfx.demo1.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupPaymentDto {
    private String paymentId;
    private double payment;
    private String paymentDate;
    private String supplierId;
}
