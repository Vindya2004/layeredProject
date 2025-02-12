package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class AllOrdersTM {
    private String orderId;
    private String orderDate;
    private String customerId;
    private String productId;
    private int quantity;
    private double price;


}
