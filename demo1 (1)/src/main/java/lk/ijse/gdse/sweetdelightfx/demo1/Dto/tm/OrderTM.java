package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderTM {
    private String orderId;
    private String orderDate;
    private double amount;
    private String customerId;
    private String paymentId;
  //  private String deliveryId;

}
