package ru.innopolis.stc12.servlets.pojo;

public class Grade {
    private int id;
    private int grade;

    public Grade(int id, int grade) {
        this.id = id;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", grade=" + grade +
                '}';
    }
}
