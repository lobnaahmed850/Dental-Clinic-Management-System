package com.mycompany.dentalclinic;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Schedulable {
    //abstract methods child class must override it
    LocalDate getDate();
    LocalTime getTime();
    String getID();
    String getStatus();
    Doctor getDoctor();
    Patient getPatient();
    Prescription getPrescription();
    void setStatus(String status);
    void setPrescription(Prescription prescription);
    void reschedule(LocalDate newDate, LocalTime newTime); 
}







