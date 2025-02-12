module lk.ijse.gdse.sweetdelightfx.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires  lombok;
    requires java.desktop;
    requires net.sf.jasperreports.core;
    requires java.security.jgss;


    opens lk.ijse.gdse.sweetdelightfx.demo1.controller to javafx.fxml;
    opens lk.ijse.gdse.sweetdelightfx.demo1.Dto.tm to javafx.base;

    exports lk.ijse.gdse.sweetdelightfx.demo1;
}