package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PaymentTM {
    private String paymentId;
    private String paymentMethod;
    private String paymentDate;


}
