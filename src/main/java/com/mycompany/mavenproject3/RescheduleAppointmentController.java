package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.Appointment;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.stage.Stage;
import java.time.LocalTime;

public class RescheduleAppointmentController {
    @FXML private DatePicker datePicker;
    @FXML private Spinner<Integer> timeSpinner;
    private Appointment appointmentToReschedule;

    public void setAppointment(Appointment appointment) {
        this.appointmentToReschedule = appointment;
        //pre-fill current values
        datePicker.setValue(appointment.getDate());
        timeSpinner.setValueFactory(new javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, appointment.getTime().getHour()));
    }
    @FXML
private void handleSave() {
    if (appointmentToReschedule == null) {
        showAlert("No appointment selected.");
        return;
    }
    if (datePicker.getValue() == null || timeSpinner.getValue() == null) {
        showAlert("Please select both a date and a time.");
        return;
    }
    //validate date & time to ensure not in the past
    LocalTime selectedTime = LocalTime.of(timeSpinner.getValue(), 0);
    if (datePicker.getValue().isBefore(java.time.LocalDate.now()) ||
        (datePicker.getValue().isEqual(java.time.LocalDate.now()) && selectedTime.isBefore(LocalTime.now()))) {
        showAlert("Please select a valid date and time. Cannot schedule in the past.");
        return;
    }
    //update appointment
    appointmentToReschedule.reschedule(datePicker.getValue(), selectedTime);
    appointmentToReschedule.setStatus("Rescheduled");
    closeWindow();
}
 @FXML
    private void handleCancel() {
        closeWindow();
    }
    private void closeWindow() {
        Stage stage = (Stage) datePicker.getScene().getWindow();
        stage.close();
    }
//alert helper
private void showAlert(String msg) {
    javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.WARNING);
    alert.setHeaderText(null);
    alert.setContentText(msg);
    alert.showAndWait();
}
   
}
