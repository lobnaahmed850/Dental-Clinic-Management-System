package com.mycompany.dentalclinic;

public class Prescription {
    private String name;
    private String frequency;
    private double price;

    public Prescription(String name, String dose, double price) {
        this.name = name;
        this.frequency = dose;
        if(price<0)
            throw new IllegalArgumentException("Prescription price can't be -ve!");
        this.price = price;
    }
    public String getName() {
        return this.name;
    }
    public String getFrequency() {
        return this.frequency;
    }
    public double getPrice() {
        return this.price;
    }
    public String displayInfo() {
        return "Prescription name: "+this.getName()+"\nPrescription frequency: "+this.getFrequency()+"\nPrescription price: "
                +this.getPrice();
    }
    
}
