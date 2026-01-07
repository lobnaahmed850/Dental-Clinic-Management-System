package com.mycompany.dentalclinic;

public abstract class Person { 
    protected String firstName; //protected data fields can be accessed inside package only and by subclasses
    protected String lastName;
    protected String email;
    protected String ID;
    
    public Person(String firstName,String lastName,String email, String ID) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.ID=ID;
    }
    public String getFirstName()  {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getID() {
        return this.ID;
    }
    
    public abstract String displayInfo(); //all child classes must override this method
       
}
