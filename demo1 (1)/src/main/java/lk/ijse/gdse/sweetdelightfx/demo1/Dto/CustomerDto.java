package lk.ijse.gdse.sweetdelightfx.demo1.Dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class CustomerDto {
    private String customerId;
    private String name;
    private String address;
    private String email;
    private String phone;
}
