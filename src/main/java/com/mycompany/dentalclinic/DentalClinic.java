package com.mycompany.dentalclinic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;

public class DentalClinic {
//ObservableList automatically updates UI when data changes
private static final ObservableList<Doctor> doctors = FXCollections.observableArrayList();
private static final ObservableList<Patient> patients = FXCollections.observableArrayList();
public static ObservableList<Payment> payments = FXCollections.observableArrayList();
public static ObservableList<Appointment> appointments = FXCollections.observableArrayList();
    
     public static ObservableList<Patient> getPatients() {
         return patients;
     }
     public static void addPatient(Patient patient) {
         patients.add(patient); 
     }
     public static void removePatient(Patient patient) {
         patients.remove(patient);
     }
     public static ObservableList<Doctor> getDoctors() {
         return doctors;
     }
     public static void addDoctor(Doctor doctor) {
         doctors.add(doctor);
     }
    public static void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }
    public static ObservableList<Appointment> getAppointments() {
        return appointments;
    }
    public static void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }
    public static void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
    public static ObservableList<Payment> getPayments() {
        return payments;
    }
    public static void addPayment(Payment payment) {
    payments.add(payment);
    }
    public static void removePayment(Payment payment) {
        payments.remove(payment);
    }
    
    //console testing

    public static void main(String[] args) {

        System.out.println("Welcome to Dental Clinic Management System :)");
        
        ArrayList<Doctor> doctors = new ArrayList<>();
        Doctor d1 = new Doctor("Sarah", "Lee", "sarah@clinic.com",
                "D02", "Orthodontist", 300, "Available", "102");
        Doctor d2 = new Doctor("Mark", "Brown", "mark@clinic.com",
                "D03", "Surgeon", 500, "Available", "103");
        try {
            Doctor d3 = new Doctor("John", "Smith", "john@clinic.com",
                    "D01", "Dental", -200, "Available", "101");
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Patient p1 = new Patient("Lara", "Stone", "lara@gmail.com",
                "P02", "Allergy", 25, 'F');
        Patient p2 = new Patient("Adam", "Miles", "adam@gmail.com",
                "P03", "Diabetes", 45, 'M');
        try {
            Patient p3 = new Patient("Tom", "Young", "tom@gmail.com",
                    "P01", "None", -5, 'M');
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }

        Appointment a1 = new Appointment("A1", p1, d1,
                LocalTime.of(10, 0),
                LocalDate.now().plusDays(1));
        Appointment a2 = new Appointment("A2", p2, d1,
                LocalTime.of(10, 0),
                LocalDate.now().plusDays(1));
        d1.setUnavailable();
        try {
            a1.checkConflict(a2);
            System.out.println("No conflict appointments.");
        } catch (AppointmentConflictException ex) {
            System.out.println(ex.getMessage());
        }

        Appointment[] appointments = {a1, a2};
        Arrays.sort(appointments);
        for (Appointment a : appointments) {
            System.out.println(a.displayInfo());
        }
        Payment pa1 = new Payment("Cash", "101", a1);
        Payment pa2 = new Payment("Credit", "102", a2);
        try {
            Prescription pr = new Prescription("Painkiller", "2/day", 200);
            pa2.setPrescroption(pr);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
        pa2.processPayment();
        pa2.refund();
        System.out.println(pa1.displayInfo());
        System.out.println(pa2.displayInfo());
    }
}
