package lk.ijse.gdse.sweetdelightfx.demo1.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetail {
    private String orderId;
    private String productId;
    private int quantity;
    private double price;

}
