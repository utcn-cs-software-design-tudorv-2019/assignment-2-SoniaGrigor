package model.persistence.repository.student;

import model.persistence.entity.Student;
import model.persistence.entity.UserCourse;

import java.util.List;

public interface StudentRepository {

    List<Student> getAll();

    boolean update(Student student);

    boolean save(Student user);

    boolean delete(Student student);

    boolean deleteById(int id);

    boolean enrollCourse(UserCourse userCourse);

    Student get(int id);

    boolean updateGrade(UserCourse userCourse);

    boolean updateGroup(int idStudent, int group);

    Student getStudentInfo(int id);

    Student findByUsernameAndPassword(String username, String encodePassword);
}
