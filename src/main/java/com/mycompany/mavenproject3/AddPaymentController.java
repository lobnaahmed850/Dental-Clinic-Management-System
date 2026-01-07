package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddPaymentController {
    @FXML private TextField paymentIdField;
    @FXML private ComboBox<Appointment> appointmentBox;
    @FXML private ComboBox<String> methodBox;
    @FXML private ComboBox<String> statusBox;
    @FXML private TextField amountField;

    @FXML
public void initialize() {
    appointmentBox.setItems(FXCollections.observableArrayList(DentalClinic.getAppointments()));
    methodBox.setItems(FXCollections.observableArrayList("Cash", "Credit"));
    statusBox.setItems(
        FXCollections.observableArrayList("Pending", "Paid"));
    appointmentBox.setOnAction(event -> {
        Appointment selectedAppt = appointmentBox.getValue();
        //automatic filled amount 
        if (selectedAppt != null && selectedAppt.getDoctor() != null) {
            amountField.setText(String.valueOf(selectedAppt.getDoctor().getFees()));
        }
    });
}
    @FXML
private void handleSubmit() {
    String paymentId = paymentIdField.getText().trim();
    if (paymentId.isEmpty()) {
        showAlert("Please enter a Payment ID.");
        return;
    }
    Appointment appt = appointmentBox.getValue();
    if (appt == null) {
        showAlert("Please select an appointment.");
        return;
    }
    String method = methodBox.getValue();
    if (method == null) {
        showAlert("Please select a payment method.");
        return;
    }
    String status = statusBox.getValue();
    if (status == null) {
        showAlert("Please select a payment status.");
        return;
    }
    Payment payment = new Payment(method,paymentId,appt);
    payment.setStatus(status);
    //attach prescription if appointment has one
if (appt.getPrescription() != null) {
    payment.setPrescroption(appt.getPrescription());
}
    DentalClinic.addPayment(payment);
    close();
}
    @FXML
    private void handleCancel() {
        close();
    }
    private void close() {
        Stage stage = (Stage) paymentIdField.getScene().getWindow();
        stage.close();
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
}
