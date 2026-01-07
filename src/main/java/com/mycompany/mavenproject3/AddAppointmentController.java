package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.Appointment;
import com.mycompany.dentalclinic.DentalClinic;
import com.mycompany.dentalclinic.Doctor;
import com.mycompany.dentalclinic.Patient;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import com.mycompany.dentalclinic.AppointmentConflictException;


public class AddAppointmentController {
    @FXML private TextField idField;
    @FXML private ComboBox<Patient> patientCombo;
    @FXML private ComboBox<Doctor> doctorCombo;
    @FXML private DatePicker datePicker;
    @FXML private Spinner<Integer> timeSpinner;
    @FXML private ComboBox<String> statusCombo;
    private Appointment addedAppointment;

    @FXML
    public void initialize() {
        patientCombo.setItems(FXCollections.observableArrayList(DentalClinic.getPatients()));
        doctorCombo.setItems(FXCollections.observableArrayList(DentalClinic.getDoctors()));
        // Time spinner 0-23 hours
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 9);
        timeSpinner.setValueFactory(valueFactory);
        statusCombo.setItems(FXCollections.observableArrayList("Scheduled","Rescheduled","Cancelled"));
        statusCombo.setValue("Scheduled");
    }
    @FXML
private void handleSubmit() {
    try {
        String id = idField.getText();
        Patient patient = patientCombo.getValue();
        Doctor doctor = doctorCombo.getValue();
        LocalDate date = datePicker.getValue();
        int hour = timeSpinner.getValue();
        String status = statusCombo.getValue();
        if(id.isEmpty() || patient == null || doctor == null || date == null) {
            showAlert("Please fill all required fields.");
            return;
        }
        if("Unavailable".equalsIgnoreCase(doctor.getStatus())) {
            showAlert("The selected doctor is unavailable right now.");
            return;
        }
        LocalTime time = LocalTime.of(hour, 0);
        LocalDateTime appointmentDateTime = LocalDateTime.of(date, time);
        if(appointmentDateTime.isBefore(LocalDateTime.now())) {
            showAlert("Please select a valid future date and time.");
            return;
        }
        addedAppointment = new Appointment(id, patient, doctor, time, date);
addedAppointment.setStatus(status);
//check conflict with existing appointment
for (Appointment existing : DentalClinic.getAppointments()) {
    addedAppointment.checkConflict(existing);
}
closeWindow();
    } 
    catch (AppointmentConflictException e) {
    showAlert(e.getMessage());
}
catch (Exception e) {
    showAlert("Unexpected error occurred.");
}
}
    @FXML
    private void handleCancel() {
        addedAppointment = null;
        closeWindow();
    }
    public Appointment getAddedAppointment() {
        return addedAppointment;
    }
    private void closeWindow() {
        Stage stage = (Stage) idField.getScene().getWindow();
        stage.close();
    }
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
    
}
