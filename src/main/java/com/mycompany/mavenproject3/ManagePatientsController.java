package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.DentalClinic;
import com.mycompany.dentalclinic.Patient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ManagePatientsController {
    @FXML private TableView<Patient> patientsTable;
    @FXML private TableColumn<Patient, String> firstNameCol;
    @FXML private TableColumn<Patient, String> lastNameCol;
    @FXML private TableColumn<Patient, String> emailCol;
    @FXML private TableColumn<Patient, String> idCol;
    @FXML private TableColumn<Patient, String> historyCol;
    @FXML private TableColumn<Patient, Integer> ageCol;
    @FXML private TableColumn<Patient, Character> genderCol;

    @FXML
    public void initialize() {
        patientsTable.setItems(DentalClinic.getPatients());
        firstNameCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getLastName()));
        emailCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEmail()));
        idCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getID()));
        historyCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getMedicalHistory()));
        ageCol.setCellValueFactory(c -> new javafx.beans.property.SimpleIntegerProperty(c.getValue().getAge()).asObject());
        genderCol.setCellValueFactory(c -> new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getGender()));     
    }
    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPatient.fxml"));
            Parent root = loader.load();
            AddPatientController controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Add Patient");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            Patient newPatient = controller.getAddedPatient();
            if (newPatient != null) {
                DentalClinic.addPatient(newPatient);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDelete() {
        Patient selected = patientsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a patient to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this patient?", ButtonType.OK, ButtonType.CANCEL);
        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
            DentalClinic.removePatient(selected);
        }
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
