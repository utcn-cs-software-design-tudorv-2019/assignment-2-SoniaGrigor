package model.business.user;

import model.business.course.CourseService;
import model.persistence.entity.Enrollment;
import model.persistence.entity.Student;
import model.persistence.entity.User;
import model.persistence.repository.student.StudentRepository;
import model.persistence.repository.user.UserRepository;

import javax.inject.Inject;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

import static model.persistence.my_utility.ProjectConstants.REPORT_FILE;

public class UserServicePostgreSQL implements UserService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private StudentRepository studentRepository;

    @Inject
    private CourseService courseService;

    public UserServicePostgreSQL() {
    }

    @Override
    public boolean update(User user) {

        return userRepository.update(user);
    }

    @Override
    public boolean save(User user) {
        return false;
    }

    @Override
    public boolean removeById(int id) {

        return userRepository.deleteById(id);
    }

    @Override
    public User findById(int idUser) {
        return userRepository.get(idUser);
    }

    @Override
    public boolean generateRaport(int idUser) {
        try {
            StringBuffer text = new StringBuffer();
            Student student = studentRepository.get(idUser);
            BufferedWriter writer = new BufferedWriter(new FileWriter(REPORT_FILE + student.getId() + student.getUsername() + ".txt"));
            text.append(student.toString() + System.getProperty("line.separator") + System.getProperty("line.separator"));
            List<Enrollment> enrollmentList = courseService.getMyCourses(idUser);
            enrollmentList.forEach(e -> text.append(e.toString() + System.getProperty("line.separator")));

            float sum = 0;
            for (int i = 0; i < enrollmentList.size(); i++) {
                sum += enrollmentList.get(i).getGrade();
            }
            float average = sum / enrollmentList.size();

            text.append(System.getProperty("line.separator") + "No of courses: " + enrollmentList.size() + System.getProperty("line.separator"));
            text.append("Average: " + average + System.getProperty("line.separator"));
            writer.write(String.valueOf(text));
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }
}
