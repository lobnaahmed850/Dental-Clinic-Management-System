package com.mycompany.dentalclinic;

public class Doctor extends Person {
    private String specialization;
    private double fees;
    private String status;
    private String clinicRoomNum;
    
    public Doctor(String firstName, String lastName, String email, String ID, String specialization,
            double fees, String status, String roomNum){
        super(firstName,lastName,email,ID); //call person constructor
        this.clinicRoomNum=roomNum;
        this.specialization=specialization;
        this.status=status;
        if(fees<0)
            throw new IllegalArgumentException("Fees can't be -ve!");
        this.fees=fees;
    }
    public String getSpecialization() {
        return this.specialization;
    }
     public double getFees() {
        return this.fees;
    }
    public String getStatus() {
        return this.status;
    }
    public String getClinicRoomNum() {
        return this.clinicRoomNum;
    }
    public void setAvailable() {
        this.status="Available";
    }
    public void setUnavailable(){
        this.status="Unavailable";
    }
    @Override
     public String displayInfo() {
         return "Doctor: "+super.getFirstName()+" "+super.getLastName()+" , "+super.getID()+" , "+
                 super.getEmail()+"\nSpecialization: "+this.getSpecialization()+"\nFees: "+this.getFees()+
                 "\nStatus: "+this.getStatus()+"\nClinic room number: "+this.getClinicRoomNum();
     }
     
}
