package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.Appointment;
import com.mycompany.dentalclinic.DentalClinic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ManageAppointmentsController {
    @FXML private TableView<Appointment> appointmentsTable;
    @FXML private TableColumn<Appointment, String> idCol;
    @FXML private TableColumn<Appointment, String> patientCol;
    @FXML private TableColumn<Appointment, String> doctorCol;
    @FXML private TableColumn<Appointment, String> dateCol;
    @FXML private TableColumn<Appointment, String> timeCol;
    @FXML private TableColumn<Appointment, String> statusCol;

    @FXML
    public void initialize() {
        appointmentsTable.setItems(DentalClinic.getAppointments());
        idCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getID()));
        patientCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getPatient().getFirstName() + " " + c.getValue().getPatient().getLastName()));
        doctorCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDoctor().getFirstName() + " " + c.getValue().getDoctor().getLastName()));
        dateCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getDate().toString()));
        timeCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getTime().toString()));
        statusCol.setCellValueFactory(c -> new javafx.beans.property.SimpleStringProperty(c.getValue().getStatus()));
    }
    @FXML
    private void handleAdd() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppointment.fxml"));
            Parent root = loader.load();
            AddAppointmentController controller = loader.getController();
            Stage stage = new Stage();
            stage.setTitle("Add Appointment");
            stage.setScene(new Scene(root));
            stage.showAndWait();
            Appointment newAppointment = controller.getAddedAppointment();
            if (newAppointment != null) {
    // Check conflict with existing appointments
    for (Appointment existing : DentalClinic.getAppointments()) {
        newAppointment.checkConflict(existing);
    }
    DentalClinic.addAppointment(newAppointment);
}
        }
        catch (RuntimeException e) {
    showAlert(e.getMessage());
}
catch (Exception e) {
    e.printStackTrace();
    showAlert("Failed to add appointment.");
}
    }
    @FXML
    private void handleCancelAppointment() {
        Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
        if(selected == null) {
            showAlert("Please select an appointment to cancel.");
            return;
        }
        selected.setStatus("Cancelled");
        appointmentsTable.refresh();
    }
    @FXML
private void handleReschedule() {
    Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();
    if(selected == null) {
        showAlert("Please select an appointment to reschedule.");
        return;
    }
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/mavenproject3/RescheduleAppointment.fxml"));
        Parent root = loader.load();
        RescheduleAppointmentController controller = loader.getController();
        controller.setAppointment(selected);
        Stage stage = new Stage();
        stage.setTitle("Reschedule Appointment");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        // Refresh TableView to reflect changes
        appointmentsTable.refresh();
    } 
    catch (IllegalArgumentException e) {
    showAlert(e.getMessage());
}
catch (Exception e) {
    e.printStackTrace();
    showAlert("Unexpected error occurred.");
}

}
@FXML
private void handleAddPrescription() {
    Appointment selected = appointmentsTable.getSelectionModel().getSelectedItem();

    if (selected == null) {
        showAlert("Please select an appointment first.");
        return;
    }
    try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("AddPrescription.fxml"));
        Parent root = loader.load();
        AddPrescriptionController controller = loader.getController();
        controller.setAppointment(selected);
        Stage stage = new Stage();
        stage.setTitle("Add Prescription");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        appointmentsTable.refresh();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
@FXML
private void handleSortAppointments() {
    FXCollections.sort(DentalClinic.getAppointments());
}
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
