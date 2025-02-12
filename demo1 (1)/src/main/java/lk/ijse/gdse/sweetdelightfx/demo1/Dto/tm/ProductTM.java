package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ProductTM {
    private String productId;
    private String productName;
    private double productPrice;
    private int productQuantity;
    private String inventory;
    private String supplierId;


}
