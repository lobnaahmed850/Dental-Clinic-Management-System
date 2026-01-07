package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.DentalClinic;
import com.mycompany.dentalclinic.Doctor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ManageDoctorsController {
    @FXML private TableView<Doctor> doctorsTable;
    @FXML private TableColumn<Doctor, String> firstNameCol;
    @FXML private TableColumn<Doctor, String> lastNameCol;
    @FXML private TableColumn<Doctor, String> emailCol;
    @FXML private TableColumn<Doctor, String> idCol;
    @FXML private TableColumn<Doctor, String> specializationCol;
    @FXML private TableColumn<Doctor, Double> feesCol;
    @FXML private TableColumn<Doctor, String> roomCol;
    @FXML private TableColumn<Doctor, String> statusCol;

    @FXML
    public void initialize() {
        doctorsTable.setItems(DentalClinic.getDoctors());
        firstNameCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getFirstName()));
        lastNameCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getLastName()));
        emailCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getEmail()));
        idCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getID()));
        specializationCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getSpecialization()));
        feesCol.setCellValueFactory(c -> new javafx.beans.property.SimpleDoubleProperty(c.getValue().getFees()).asObject());
        roomCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getClinicRoomNum()));
        statusCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus()));
    }
    @FXML
    private void handleAdd() {
        try {
            //load fxml file from resources 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDoctor.fxml"));
            //read fxml file and create all components
            //root is the top UI node
            Parent root = loader.load();
            //pass data back to management window
            AddDoctorController controller = loader.getController();
            //create a new independent window
            Stage stage = new Stage();
            stage.setTitle("Add Doctor");
            //stage can't show anything without a scene
            stage.setScene(new Scene(root));
            stage.showAndWait();
            Doctor newDoctor = controller.getAddedDoctor();
            if (newDoctor != null) {
                DentalClinic.addDoctor(newDoctor);
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleDelete() {
        Doctor selected = doctorsTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Please select a doctor to delete.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete this doctor?", ButtonType.OK, ButtonType.CANCEL);
        if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) 
            DentalClinic.removeDoctor(selected);
        //if(user explicitly pressed OK) remove doctor, otherwise(cancel/close window) don't remove
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
}
