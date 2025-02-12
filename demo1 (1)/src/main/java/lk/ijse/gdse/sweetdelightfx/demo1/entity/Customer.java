package lk.ijse.gdse.sweetdelightfx.demo1.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Customer {
    private String customerId;
    private String name;
    private String address;
    private String email;
    private String phone;
}
