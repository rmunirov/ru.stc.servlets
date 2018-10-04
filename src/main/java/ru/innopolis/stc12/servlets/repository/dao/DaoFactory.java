package ru.innopolis.stc12.servlets.repository.dao;

public class DaoFactory {
    public static CityDao getCityDao() {
        return new CityDao();
    }

    public static DepartmentDao getDepartmentDao() {
        return new DepartmentDao();
    }

    public static DisciplineDao getDisciplineDao() {
        return new DisciplineDao();
    }

    public static GradeDao getGradeDao() {
        return new GradeDao();
    }

    public static GroupDao getGroupDao() {
        return new GroupDao();
    }

    public static PerfomanceDao getPerfomanceDao() {
        return new PerfomanceDao();
    }

    public static SexDao getSexDao() {
        return new SexDao();
    }

    public static StudentDao getStudentDao() {
        return new StudentDao();
    }

    public static TeacherDao getTeacherDao() {
        return new TeacherDao();
    }
}
