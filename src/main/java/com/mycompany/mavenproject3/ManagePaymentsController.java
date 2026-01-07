package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.DentalClinic;
import com.mycompany.dentalclinic.Payment;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ManagePaymentsController {
    @FXML private TableView<Payment> paymentsTable;
    @FXML private TableColumn<Payment, String> paymentIdCol;
    @FXML private TableColumn<Payment, String> methodCol;
    @FXML private TableColumn<Payment, String> appointmentIdCol;
    @FXML private TableColumn<Payment, String> amountCol;
    @FXML private TableColumn<Payment, String> statusCol;
    @FXML private TableColumn<Payment, String> prescNameCol;
    @FXML private TableColumn<Payment, String> prescFreqCol;
    @FXML private TableColumn<Payment, String> prescPriceCol;

    @FXML
    public void initialize() {
       paymentsTable.setItems(DentalClinic.getPayments());
        paymentIdCol.setCellValueFactory(c ->
                new SimpleStringProperty(String.valueOf(c.getValue().getID())));
        methodCol.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getMethod()));
        appointmentIdCol.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getAppointment().getID()));
        amountCol.setCellValueFactory(c ->
                new SimpleStringProperty(String.valueOf(c.getValue().getAmount())));
        statusCol.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().getStatus()));
        prescNameCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getPrescription() == null ? "-" :
                                c.getValue().getPrescription().getName()
                ));
        prescFreqCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getPrescription() == null ? "-" :
                                c.getValue().getPrescription().getFrequency()
                ));
        prescPriceCol.setCellValueFactory(c ->
                new SimpleStringProperty(
                        c.getValue().getPrescription() == null ? "-" :
                                String.valueOf(c.getValue().getPrescription().getPrice())
                ));
    }
    @FXML
private void handleAdd() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPayment.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Payment");
        stage.setScene(new Scene(root));
        stage.showAndWait();
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
}
@FXML
private void handleDelete() {
    Payment selected = paymentsTable.getSelectionModel().getSelectedItem();
    if (selected == null) {
            showAlert("Please select a patient to delete.");
            return;
        }
    Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this payment?", ButtonType.OK, ButtonType.CANCEL);
        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            DentalClinic.removePayment(selected);
        }
}
@FXML
private void handleRefund() {
    Payment selected = paymentsTable.getSelectionModel().getSelectedItem();
    if (selected == null) {
        showAlert(Alert.AlertType.WARNING,
                "No Selection",
                "Please select a payment to refund.");
        return;
    }
    String status = selected.getStatus();
    if ("Paid".equalsIgnoreCase(status)) {
        selected.setStatus("Refunded");
        paymentsTable.refresh();
        showAlert(Alert.AlertType.INFORMATION,
                "Refund Successful",
                "Payment has been refunded successfully.");
    } else if ("Pending".equalsIgnoreCase(status)) {
        showAlert(Alert.AlertType.ERROR,
                "Refund Not Allowed",
                "This payment is still pending and cannot be refunded.");
    } else if ("Refunded".equalsIgnoreCase(status)) {
        showAlert(Alert.AlertType.INFORMATION,
                "Already Refunded",
                "This payment has already been refunded.");
    }
}
private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
private void showAlert(Alert.AlertType type, String title, String msg) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}

}
