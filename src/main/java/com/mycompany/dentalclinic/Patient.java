package com.mycompany.dentalclinic;

public class Patient extends Person {
    private String medicalHistory;
    private int age;
    private char gender;
    
    public Patient(String firstName, String lastName, String email, String ID, String medicalHistory,
            int age, char gender) {
        super(firstName,lastName,email,ID); //call person constructor
        this.medicalHistory=medicalHistory;
        this.gender=gender;
        if(age<0 || age>120)
            throw new IllegalArgumentException("Invalid value of age!");
         this.age=age;
    }
    public String getMedicalHistory() {
        return medicalHistory;
    }
    public int getAge() {
        return age;
    }
    public char getGender() {
        return gender;
    }
    @Override
     public String displayInfo() {
         return "Patient: "+super.getFirstName()+" "+super.getLastName()+" , "+super.getID()+
                 " , "+super.getEmail()+"\nMedical history: "+this.getMedicalHistory()+
                 "\nAge: "+this.getAge()+"\nGender: "+this.getGender();
     }
     
}
