package model.business.student;

import org.hibernate.SessionFactory;
import model.persistence.entity.Student;
import model.persistence.entity.StudentPersonalInfo;
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.security.RightsRolesRepository;
import model.persistence.repository.student.StudentRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class StudentServicePostgreSQL implements StudentService {
    @Inject
    private StudentRepository studentRepository;
    @Inject
    private RightsRolesRepository rightsRolesRepository;
    @Inject
    private CourseRepository courseRepository;

    public StudentServicePostgreSQL(SessionFactory connection) {
    }

    @Override
    public int findIdByUsernameAndPassword(String username, String password) {
        return studentRepository.findIdByUsernameAndPassword(username,password);
    }

    @Override
    public List<StudentPersonalInfo> findAll() {
        return studentRepository.getAll();
    }

    @Override
    public boolean update(Student student) {
        return studentRepository.update(student);
    }

    @Override
    public boolean save(Student user) {
        return studentRepository.save(user);
    }

    @Override
    public boolean removeAll() {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return studentRepository.deleteById(id);
    }

    @Override
    public boolean enrollCourse(int idUser, int idCourse) {
        return studentRepository.enrollCourse(idUser, idCourse);
    }

    @Override
    public void enrollCourses(int idUser, ArrayList<Integer> idCourses) {
       for(int idCourse=0;idCourse<idCourses.size();idCourse++){
           studentRepository.enrollCourse(idUser, idCourse);
       }
    }

    @Override
    public Student findById(int idUser) {
        return studentRepository.get(idUser);
    }

    @Override
    public void updateGrade(int idStudent, int idCourse,int grade) {
        studentRepository.updateGrade(idStudent,idCourse, grade);
    }

    @Override
    public boolean updateGroup(int idStudent, String group) {
        return studentRepository.updateGroup(idStudent,group);
    }

}
