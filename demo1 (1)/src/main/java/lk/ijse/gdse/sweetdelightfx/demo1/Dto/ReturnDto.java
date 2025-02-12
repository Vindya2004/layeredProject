package lk.ijse.gdse.sweetdelightfx.demo1.Dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString


public class ReturnDto {
    private String retutnId;
    private LocalDate returnDate;
}
