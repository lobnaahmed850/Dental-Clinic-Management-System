package com.mycompany.dentalclinic;

public interface Billable {
    //abstract methods child class must override it
    double getAmount();
    String getID();
    String getMethod();
    String getStatus();
    Appointment getAppointment();
    Prescription getPrescription();
    void processPayment();
}
