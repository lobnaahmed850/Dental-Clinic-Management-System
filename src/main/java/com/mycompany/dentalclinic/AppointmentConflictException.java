package com.mycompany.dentalclinic;

public class AppointmentConflictException extends RuntimeException { //customized exception class
    public AppointmentConflictException(String message) {
        super(message); //call RuntimeException class constructor
    }
}
