package com.mycompany.dentalclinic;

public class Payment implements Billable {
    private String status;
    private String paymentMethod;
    private double amount;
    private String paymentID;
    private Appointment appointment;
    private Prescription prescription;
    
    public Payment(String paymentMethod, String paymentID, Appointment appointment) {
        this.paymentMethod = paymentMethod;
        this.paymentID = paymentID;
        this.appointment = appointment;
        this.status = "Pending";
        this.amount = appointment.getDoctor().getFees();
    }
    @Override
    public String getID() {
        return paymentID;
    }
    @Override
    public String getMethod() {
        return paymentMethod;
    }
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public Appointment getAppointment() {
        return appointment;
    }
    @Override
    public Prescription getPrescription() {
        return prescription;
    }
    @Override
    public double getAmount() { //return doctor fees attached to this appointment
        return amount;
    }
    public double getTotalAmount() { //return doctor fees+prescription price attached to this appointment
        double prescriptionPrice =(prescription != null) ? prescription.getPrice() : 0.0;
        return amount + prescriptionPrice;
    }
    public void setPrescroption(Prescription prescroption) {
        this.prescription = prescroption;
    }
    public void setStatus(String status) {
        this.status=status;
    }
    @Override
    public void processPayment() {
        this.status = "Paid";
    }
    public void refund() { //to refund amount, status of payment must be "Paid"
        if (!status.equals("Paid"))
            throw new IllegalStateException("Payment isn't completed!");
        this.status = "Refunded";
    }
    public String displayInfo() {
        return "Payment method: "+this.getMethod()+" , Payment ID: "+this.getID()+" , Payment status: "
                +this.getStatus()+" , Total price: "+this.getTotalAmount();
    }
    
}
