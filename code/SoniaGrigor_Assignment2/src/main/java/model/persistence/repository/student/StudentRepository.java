package model.persistence.repository.student;

import model.persistence.entity.Student;
import model.persistence.entity.StudentPersonalInfo;

import java.util.List;

public interface StudentRepository {

    int findIdByUsernameAndPassword(String username, String password);

    List<StudentPersonalInfo> getAll();

    boolean update(Student student);

    boolean save(Student user);

    boolean delete(Student student);

    boolean deleteAll();

    boolean deleteById(int id);

    boolean enrollCourse(int idUser, int idCourse);

    Student get(int id);

    void updateGrade(int idStudent, int idCourse, int grade);

    boolean updateGroup(int idStudent, String group);
}
