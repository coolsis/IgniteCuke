package com.ignite.pojos;

import com.github.javafaker.Faker;

public class Parent {
    public String firstName;
    public String lastName;
    public String hdyhau = "Other";
    public String street;
    public String city = "Richardson";
    public String state = "Texas";
    public int zipCode = 75044;
    public String email;
    public String homeNumber;

    public Parent() {
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        street = faker.address().streetAddress();
        email = faker.internet().emailAddress();
        homeNumber = faker.number().digits(7) ;
    }

    @Override
    public String toString() {
        return "\nParent {" + "\n" +
                "\tFirst Name:" + firstName + "\n" +
                "\tLast Name:" + lastName + "\n" +
                "\tFull Address:" + street + " " + city + ", " + state + " " + zipCode + "\n" +
                "\tEmail:" + email + "\n" +
                "\tHome phone:" + homeNumber + "\n" +
                '}'+"\n\n\n";
    }


}