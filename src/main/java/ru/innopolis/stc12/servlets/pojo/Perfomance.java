package ru.innopolis.stc12.servlets.pojo;

public class Perfomance {
    private int id;
    private Discipline discipline;
    private Student student;
    private Grade grade;
    private String description;

    public Perfomance(int id, Discipline discipline, Student student, Grade grade, String description) {
        this.id = id;
        this.discipline = discipline;
        this.student = student;
        this.grade = grade;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Perfomance{" +
                "id=" + id +
                ", discipline=" + discipline +
                ", student=" + student +
                ", grade=" + grade +
                ", description='" + description + '\'' +
                '}';
    }
}
