package com.mycompany.mavenproject3;

import com.mycompany.dentalclinic.Appointment;
import com.mycompany.dentalclinic.DentalClinic;
import com.mycompany.dentalclinic.Doctor;
import com.mycompany.dentalclinic.Patient;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController {

    @FXML private Button manageDoctorsBtn;
    @FXML private Button managePatientsBtn;
    @FXML private Button addDoctorBtn;
    @FXML private Button addPatientBtn;
    @FXML private Button addAppointmentBtn;
    @FXML private Button manageAppointmentsBtn;
    @FXML private Button addPaymentBtn;
    @FXML private Button managePaymentsBtn;

     @FXML
private void handleAddPatient() {
    System.out.println("Add Patient clicked"); //for console window
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/mavenproject3/AddPatient.fxml"));
        Parent root = loader.load();
        AddPatientController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Add Patient");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        Patient newPatient = controller.getAddedPatient();
        if (newPatient != null) {
            //add to the main list so management screens see it
            DentalClinic.addPatient(newPatient);
        }
        System.out.println("Add Patient window shown"); //for console window
    } 
    catch (Exception e) {
        e.printStackTrace();
    }
}
@FXML
void handleManagePatients() {
    try {
        Parent root = FXMLLoader.load(
                getClass().getResource("ManagePatients.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Manage Patients");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
@FXML
void handleAddDoctor() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddDoctor.fxml"));
        Parent root = loader.load();

        AddDoctorController controller = loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Add Doctor");
        stage.setScene(new Scene(root));
        stage.showAndWait(); // wait until window closes
        Doctor newDoctor = controller.getAddedDoctor();
        if (newDoctor != null) {
            DentalClinic.addDoctor(newDoctor); //add to main list
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}
   @FXML
void handleManageDoctors(ActionEvent event) {
    openWindow("ManageDoctors.fxml", "Manage Doctors");
}
@FXML
void handleAddAppointment(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddAppointment.fxml")); // adjust path
        Parent root = loader.load();
        AddAppointmentController controller= loader.getController();
        Stage stage = new Stage();
        stage.setTitle("Add Appointment");
        stage.setScene(new Scene(root));
        stage.showAndWait();
        Appointment newAppointment = controller.getAddedAppointment();
        if(newAppointment != null) {
            DentalClinic.addAppointment(newAppointment);
        }
    } catch (Exception e) {
        e.printStackTrace();
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Failed to open Add Appointment screen");
        alert.setContentText(e.getMessage());
        alert.showAndWait();
    }
}
@FXML
    void handleManageAppointments(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/mycompany/mavenproject3/ManageAppointments.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Manage Appointments");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Failed to open Manage Appointments screen.");
    }
}
    @FXML
void handleAddPayment(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/mavenproject3/AddPayment.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Add Payment");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Failed to open Add Payment screen.");
    }
}
@FXML
private void handleManagePayments(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/mycompany/mavenproject3/ManagePayments.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setTitle("Manage Payments");
        stage.setScene(new Scene(root));
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
        showAlert("Failed to open Manage Payments screen.");
    }
}
//helper method 
private void openWindow(String fxml, String title) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource(fxml));
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    @FXML
    void handleAbout(ActionEvent event) { showAlert("Dental Clinic Management System v1.0"); }
    @FXML
    void handleExit(ActionEvent event) {
        Stage stage = (Stage) manageDoctorsBtn.getScene().getWindow();
        stage.close();
    }
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
   
}
