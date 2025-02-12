package lk.ijse.gdse.sweetdelightfx.demo1.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupPayment {
    private String paymentId;
    private double payment;
    private String paymentDate;
    private String supplierId;
}
