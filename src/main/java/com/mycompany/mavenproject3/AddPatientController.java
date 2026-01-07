package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.DentalClinic;
import com.mycompany.dentalclinic.Patient;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddPatientController {
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField idField;
    @FXML private TextField historyField;
    @FXML private TextField ageField;
    @FXML private ComboBox<String> genderComboBox;
    private Patient addedPatient;

    @FXML
    public void initialize() {
        genderComboBox.getItems().addAll("M", "F");
    }
    @FXML
private void handleSave() {
    try {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String id = idField.getText().trim();
        String history = historyField.getText().trim();
        String ageText = ageField.getText().trim();
        String genderValue = genderComboBox.getValue();
        //check for empty fields
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || id.isEmpty()
                || history.isEmpty() || ageText.isEmpty() || genderValue == null) {
            showError("Please fill all fields.");
            return;
        }
        //parse and validate age
        int age = Integer.parseInt(ageText);
        //create patient object
        addedPatient = new Patient(firstName, lastName, email, id, history, age, genderValue.charAt(0));
        closeWindow();
    } 
    catch (NumberFormatException e) {
        showError("Age must be a numeric value.");
    } 
    catch (Exception e) {
        showError("Invalid input. Please check all fields.");
    }
}
    @FXML
    private void handleCancel() {
        addedPatient = null;
        closeWindow();
    }
    public Patient getAddedPatient() {
        return addedPatient;
    }
    private void closeWindow() {
        Stage stage = (Stage) firstNameField.getScene().getWindow();
        stage.close();
    }
    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
