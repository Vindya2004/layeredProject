package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SupPaymentTM {
    private String paymentId;
    private double payment;
    private String paymentDate;
    private String supplierId;


}
