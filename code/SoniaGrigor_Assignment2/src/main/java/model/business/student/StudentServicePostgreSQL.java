package model.business.student;

import model.persistence.entity.Student;
import model.persistence.entity.User;
import model.persistence.entity.UserCourse;
import model.persistence.my_utility.UtilityAuthorization;
import model.persistence.repository.student.StudentRepository;
import model.persistence.repository.user.UserCourseRepository;
import model.persistence.repository.user.UserRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;

import static model.business.user.AuthenticationServicePostgreSQL.encodePassword;

public class StudentServicePostgreSQL implements StudentService {

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserCourseRepository userCourseRepository;

    public StudentServicePostgreSQL() {
    }

    @Override
    public List<Student> findAll() {
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
    public boolean removeById(int id) {
        userRepository.deleteById(userRepository.findByUsernameAndPassword(studentRepository.get(id).getUsername(), studentRepository.get(id).getPassword()).getId());
        return studentRepository.deleteById(id);
    }

    @Override
    public boolean enrollCourse(int idUser, int idCourse) {
        User user = userRepository.findByUsernameAndPassword(studentRepository.get(idUser).getUsername(), studentRepository.get(idUser).getPassword());
        UserCourse userCourse = new UserCourse(user.getId(), idCourse, 0);
        return studentRepository.enrollCourse(userCourse);
    }

    @Override
    public Student findById(int idUser) {
        return studentRepository.getStudentInfo(idUser);
    }

    @Override
    public boolean updateGrade(int idStudent, int idCourse, int grade) {
        User user = userRepository.findByUsernameAndPassword(studentRepository.get(idStudent).getUsername(), studentRepository.get(idStudent).getPassword());
        UserCourse userCourseOld = userCourseRepository.getByDetails(user.getId(), idCourse);
        UserCourse userCourse = new UserCourse(userCourseOld.getId(), user.getId(), idCourse, grade);
        return studentRepository.updateGrade(userCourse);
    }

    @Override
    public boolean updateGroup(int idStudent, int group) {
        return studentRepository.updateGroup(idStudent, group);
    }

    @Override
    public boolean login(String username, String password) throws IOException, IndexOutOfBoundsException {
        Student user = studentRepository.findByUsernameAndPassword(username, encodePassword(password));
        UtilityAuthorization.setLoggedUser(user.getId());

        return user != null;
    }
}
