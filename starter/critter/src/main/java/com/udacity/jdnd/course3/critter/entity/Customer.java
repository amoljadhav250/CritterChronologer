package com.udacity.jdnd.course3.critter.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id=0;

    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(
            mappedBy="owner")
    private List<Pet> pets = new ArrayList<>();

    public Customer(String name, String phoneNumber, String notes, List<Pet> pets) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public Customer(){
        //pets = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return this.pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = this.pets;
    }


    public Customer(long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pets=" + pets +
                '}';
    }
}
