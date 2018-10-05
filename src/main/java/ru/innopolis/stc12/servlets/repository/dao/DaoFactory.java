package ru.innopolis.stc12.servlets.repository.dao;

public class DaoFactory {
    private DaoFactory() {
    }

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

    public static PerformanceDao getPerfomanceDao() {
        return new PerformanceDao();
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
