package model.persistence.repository.student;

import org.hibernate.SessionFactory;
import model.persistence.entity.Student;
import model.persistence.entity.StudentPersonalInfo;
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.security.RightsRolesRepository;

import javax.inject.Inject;
import java.util.List;

public class StudentRepositoryPostgreSQL implements StudentRepository {

    private final SessionFactory sessionFactory;
    @Inject
    private RightsRolesRepository rightsRolesRepository;
    @Inject
    private CourseRepository courseRepository;

    public StudentRepositoryPostgreSQL(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public int findIdByUsernameAndPassword(String username, String password) {
        return 0;
    }

    @Override
    public List<StudentPersonalInfo> getAll() {
        return null;
    }

    @Override
    public boolean update(Student student) {
        return false;
    }

    @Override
    public boolean save(Student user) {
        return false;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        return false;
    }

    @Override
    public boolean delete(Student student) {
        return false;
    }

    @Override
    public boolean enrollCourse(int idUser, int idCourse) {
        return false;
    }

    @Override
    public Student get(int id) {
        return null;
    }

    @Override
    public void updateGrade(int idStudent, int idCourse, int grade) {

    }

    @Override
    public boolean updateGroup(int idStudent, String group) {
        return false;
    }
}
