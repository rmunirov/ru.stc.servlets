package ru.innopolis.stc12.servlets.pojo;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String surname;
    private Sex sex;
    private Date dateOfReceipt;
    private Group group;
    private String address;
    private String phone;
    private String email;
    private City city;

    public Student(int id, String name, String surname, Sex sex, Date dateOfReceipt, Group group, String address, String phone, String email, City city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfReceipt = dateOfReceipt;
        this.group = group;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public Student(String name, String surname, Sex sex, Date dateOfReceipt, Group group, String address, String phone, String email, City city) {
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfReceipt = dateOfReceipt;
        this.group = group;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Date getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(Date dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", sex=" + sex +
                ", dateOfReceipt=" + dateOfReceipt +
                ", group=" + group +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", city=" + city +
                '}';
    }
}
