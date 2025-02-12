package lk.ijse.gdse.sweetdelightfx.demo1.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetailDto {
    private String orderId;
    private String productId;
    private int quantity;
    private double price;

}
