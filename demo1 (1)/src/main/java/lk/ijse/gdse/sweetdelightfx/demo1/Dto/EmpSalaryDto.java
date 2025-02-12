package lk.ijse.gdse.sweetdelightfx.demo1.Dto;

import javafx.fxml.FXML;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class EmpSalaryDto {
    private String payId;
    private double salary;
    private String payDate;
    private String empId;

}
