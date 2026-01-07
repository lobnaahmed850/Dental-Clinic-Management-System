package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.Appointment;
import com.mycompany.dentalclinic.Prescription;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddPrescriptionController {
    @FXML private TextField nameField;
    @FXML private TextField freqField;
    @FXML private TextField priceField;
    private Appointment appointment;
    
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
    @FXML
private void handleSave() {
    String name = nameField.getText().trim();
    String freq = freqField.getText().trim();
    String priceText = priceField.getText().trim();
    //check for empty fields
    if (name.isEmpty() || freq.isEmpty() || priceText.isEmpty()) {
        showAlert("Please fill all fields.");
        return;
    }
    double price;
    try {
        price = Double.parseDouble(priceText);
    } 
    catch (NumberFormatException e) {
        showAlert("Price must be a numeric value.");
        return;
    }
    try {
        //will throw IllegalArgumentException if price < 0
        Prescription prescription = new Prescription(name, freq, price);
        appointment.setPrescription(prescription);
        close();
    } catch (IllegalArgumentException e) {
        showAlert(e.getMessage()); 
    }
}
@FXML
    private void handleCancel() {
        close();
    }
    private void close() {
        Stage stage = (Stage) nameField.getScene().getWindow();
        stage.close();
    }
//alert helper
private void showAlert(String msg) {
    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}
    
}
