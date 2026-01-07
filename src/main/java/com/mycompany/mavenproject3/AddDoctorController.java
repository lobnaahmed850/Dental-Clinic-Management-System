package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.DentalClinic;
import com.mycompany.dentalclinic.Doctor;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddDoctorController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField idField;
    @FXML private TextField feesField;
    @FXML private TextField roomField;
    @FXML private ComboBox<String> specializationBox;
    @FXML private ComboBox<String> statusBox;
    private Doctor addedDoctor;

    @FXML
    public void initialize() {
        specializationBox.getItems().addAll("Orthodontist", "Surgeon", "Pediatric", "General");
        statusBox.getItems().addAll("Available", "Unavailable");
    }
    @FXML
private void handleSave() {
    try {
        //check all required fields
        if (firstNameField.getText().isEmpty() ||
            lastNameField.getText().isEmpty() ||
            emailField.getText().isEmpty() ||
            idField.getText().isEmpty() ||
            feesField.getText().isEmpty() ||
            roomField.getText().isEmpty() ||
            specializationBox.getValue() == null ||
            statusBox.getValue() == null) {
            showAlert("Please fill all required fields.");
            return;
        }
        //create doctor if all validations pass
        addedDoctor = new Doctor(
                firstNameField.getText(),
                lastNameField.getText(),
                emailField.getText(),
                idField.getText(),
                specializationBox.getValue(),
                Double.parseDouble(feesField.getText()),
                statusBox.getValue(),
                roomField.getText()
        );
        closeWindow();
    } 
    catch (NumberFormatException e) {
        showAlert("Invalid fees. Please enter a valid number.");
    } 
    catch (Exception e) {
        showAlert("Error adding doctor: " + e.getMessage());
    }
}
    @FXML
    private void handleCancel() {
        addedDoctor = null;
        closeWindow();
    }
    public Doctor getAddedDoctor() {
        return addedDoctor;
    }
    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
