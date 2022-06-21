module com.mycompany.proyectoedgrupo7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyectoedgrupo7 to javafx.fxml;
    exports com.mycompany.proyectoedgrupo7;
}
