package ru.innopolis.stc12.servlets.pojo;

public class Teacher {
    private int id;
    private String name;
    private String surname;
    private Department department;
    private PersonalData personalData;
    private Sex sex;

    public Teacher(int id, String name, String surname, Department department, PersonalData personalData, Sex sex) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.personalData = personalData;
        this.sex = sex;
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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", department=" + department +
                ", personalData=" + personalData +
                ", sex=" + sex +
                '}';
    }
}
