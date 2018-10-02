package ru.innopolis.stc12.servlets.pojo;

import java.util.Date;

public class Student {
    private int id;
    private String name;
    private String surname;
    private Sex sex;
    private Date dateOfReceipt;
    private Group group;
    private PersonalData personalData;

    public Student(int id, String name, String surname, Sex sex, Date dateOfReceipt, Group group, PersonalData personalData) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.sex = sex;
        this.dateOfReceipt = dateOfReceipt;
        this.group = group;
        this.personalData = personalData;
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

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
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
                ", personalData=" + personalData +
                '}';
    }
}
