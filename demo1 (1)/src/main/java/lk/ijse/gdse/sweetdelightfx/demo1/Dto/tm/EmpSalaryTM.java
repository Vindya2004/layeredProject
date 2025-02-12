package lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmpSalaryTM {
    private String payId;
    private double salary;
    private String payDate;
    private String empId;
}
