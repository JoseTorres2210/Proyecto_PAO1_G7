module com.mycompany.proyectoedgrupo7 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.mycompany.proyectoedgrupo7 to javafx.fxml;
    
    /*
    #OJO CON LA LINEA DE AQUI ABAJO
    */
    opens modelo to javafx.base;
    exports com.mycompany.proyectoedgrupo7;
}
