package com.mycompany.dentalclinic;

import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment implements Comparable<Appointment>,Schedulable {
    private String appointmentID;
    private Patient patient;
    private Doctor doctor;
    private LocalTime appointmentTime;
    private LocalDate appointmentDate;
    private String status;
    private Prescription prescription;
    
    public Appointment( String appointmentID,Patient patient, Doctor doctor,
            LocalTime appointmentTime, LocalDate appointmentDate) {
        this.appointmentID = appointmentID;
        this.patient = patient;
        this.doctor = doctor;
        this.appointmentTime = appointmentTime;
        this.appointmentDate = appointmentDate;
        this.status = "Scheduled";
    }
    @Override
    public String getID() {
        return this.appointmentID;
    }
    @Override
    public Patient getPatient(){
        return this.patient;
    }
    @Override
    public Doctor getDoctor() {
        return this.doctor;
    }
    @Override
    public LocalTime getTime() {
        return this.appointmentTime;
    }
    @Override
    public LocalDate getDate() {
        return this.appointmentDate;
    }
    @Override
    public String getStatus() {
        return this.status;
    }
    @Override
    public Prescription getPrescription() {
    return this.prescription;
    }
    @Override
    public void setStatus(String status) {
        this.status=status;
    }
    @Override
    public void setPrescription(Prescription prescription) {
    this.prescription = prescription;
    }
    @Override
    public void reschedule(LocalDate newDate, LocalTime newTime) { 
        if(newDate.isBefore(LocalDate.now())||(newDate.isEqual(LocalDate.now())&& newTime.isBefore(LocalTime.now())))
            throw new IllegalArgumentException("New date/time can't be in the past!");
        this.appointmentDate=newDate;
        this.appointmentTime=newTime;
        this.status="Rescheduled";
    }
    public void cancel() { 
        this.status="Cancelled";
    }
    @Override
    public int compareTo(Appointment a) { //override a method from Comparable interface 
        int dateCompare=this.appointmentDate.compareTo(a.appointmentDate);
        if(dateCompare!=0) return dateCompare;
        return this.appointmentTime.compareTo(a.appointmentTime);
    }
    public boolean conflictWith(Appointment a) { //helper method to check if two appointments conflict with each other
        return this.appointmentDate.equals(a.appointmentDate) &&
                this.appointmentTime.equals(a.appointmentTime)
                && this.doctor.getID().equals(a.doctor.getID());
    }
    public void checkConflict (Appointment a) {
        if(this.conflictWith(a)) 
            throw new AppointmentConflictException ("Appointments conflict!");
    }
     public String displayInfo() {
	return "Appointment ID: "+this.getID()+" , "+this.getDate()+
                " , "+this.getTime()+" , "+"Status: "+this.getStatus();	
	}
}
