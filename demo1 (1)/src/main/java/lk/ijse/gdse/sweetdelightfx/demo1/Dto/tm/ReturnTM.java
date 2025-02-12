package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ReturnTM {
    private String returnId;
    private LocalDate returnDate;
}
