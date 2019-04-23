package model.business.student;

import model.persistence.entity.Student;

import java.io.IOException;
import java.util.List;

public interface StudentService {

    List<Student> findAll();

    boolean update(Student student);

    boolean save(Student user);

    boolean removeById(int id);

    boolean enrollCourse(int idUser, int idCourse);

    Student findById(int idUser);

    boolean updateGrade(int idStudent, int idCourse, int grade);

    boolean updateGroup(int idStudent, int group);

    boolean login(String username, String password) throws IOException, IndexOutOfBoundsException;
}
