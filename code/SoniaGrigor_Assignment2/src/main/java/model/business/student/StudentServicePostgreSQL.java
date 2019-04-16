package model.business.student;

import model.persistence.entity.Student;
import model.persistence.entity.StudentPersonalInfo;
import model.persistence.my_utility.Utility;
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.security.RightsRolesRepository;
import model.persistence.repository.student.StudentRepository;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static model.business.user.AuthenticationServicePostgreSQL.encodePassword;

public class StudentServicePostgreSQL implements StudentService {
    private StudentRepository studentRepository;
    private RightsRolesRepository rightsRolesRepository;
    private CourseRepository courseRepository;

    public StudentServicePostgreSQL(SessionFactory session,RightsRolesRepository rightsRolesRepository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.rightsRolesRepository=rightsRolesRepository;
        this.courseRepository=courseRepository;
        this.studentRepository=studentRepository;
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
        return studentRepository.getStudentInfo(idUser);
    }

    @Override
    public void updateGrade(int idStudent, int idCourse,int grade) {
        studentRepository.updateGrade(idStudent,idCourse, grade);
    }

    @Override
    public boolean updateGroup(int idStudent, int group) {
        return studentRepository.updateGroup(idStudent,group);
    }

    @Override
    public boolean login(String username, String password) throws IOException, IndexOutOfBoundsException{
        Student user = studentRepository.findByUsernameAndPassword(username, encodePassword(password));
        Utility.setLoggedUser(user.getId());

        return user != null;
    }
}
