package ru.innopolis.stc12.servlets.pojo;

import java.util.Date;

public class PersonalData {
    private int id;
    private Date dateOfBirth;
    private City city;
    private String address;
    private String phone;
    private String email;

    public PersonalData(int id, Date dateOfBirth, City city, String address, String phone, String email) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.city = city;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", city=" + city +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
