module com.mycompany.mavenproject3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;

    opens com.mycompany.mavenproject3 to javafx.fxml;
    opens com.mycompany.dentalclinic to javafx.fxml; // changed from javafx.base

    exports com.mycompany.mavenproject3;
    exports com.mycompany.dentalclinic;
}
