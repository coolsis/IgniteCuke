package com.ignite.pojos;


import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Student {

    public String parentRelation;
    public String firstName;
    public String lastName;
    public String dateOfBird;
    public String gender;
    public boolean currentAcademicYear = true;
    public String gradeLevel = "04";

    List<String> relationShipList = Arrays.asList("Father","Grandmother", "Grandfather","Guardian","Mother", "Other","Step-father","Step-mother");
    List<String> genderList = Arrays.asList("Female","Male");

    public Student() {
        Faker createNew = new Faker();
        firstName = createNew.name().firstName();
        lastName = createNew.name().lastName();

        dateOfBird = getDoB(createNew.date().birthday(4,11));
        parentRelation = relationShipList.get(createNew.number().numberBetween(0,relationShipList.size()));

        // altarnative way with Random()
        // parentRelation = relationShipList.get(new Random().nextInt(relationShipList.size()));

        gender = genderList.get(createNew.number().numberBetween(0,2));
    }


    public String getDoB (Date date){
        return new SimpleDateFormat("MM/dd/YYYY").format(date);
    }

    @Override
    public String toString() {
        return "\nStudent {" + "\n" +
                "\tParent Relation: " + parentRelation + "\n" +
                "\tFirst Name: " + firstName + "\n" +
                "\tLast Name: " + lastName + "\n" +
                "\tDate Of Bird: " + dateOfBird + "\n" +
                "\tGender: " + gender + "\n" +
                "\tAcademic Year: " + (currentAcademicYear==true?"Current Academic Year":"Next Academic Year") +  "\n" +
                "\tGrade Level: " + gradeLevel + "\n" +
                '}'+"\n\n\n";
    }
}