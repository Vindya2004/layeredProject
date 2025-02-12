package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetailTM {
    private String productId;
    private String quantity;
    private int orderId;
}
