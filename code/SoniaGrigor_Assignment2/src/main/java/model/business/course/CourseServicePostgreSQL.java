package model.business.course;

import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;
import model.persistence.entity.User;
import model.persistence.entity.UserCourse;
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.student.StudentRepository;
import model.persistence.repository.user.UserCourseRepository;
import model.persistence.repository.user.UserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CourseServicePostgreSQL implements CourseService {

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private UserRepository userRepository;

    @Inject
    private UserCourseRepository userCourseRepository;

    @Inject
    private StudentRepository studentRepository;

    public CourseServicePostgreSQL() {
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.getAll();
    }

    @Override
    public boolean save(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Enrollment> getMyCourses(int id) {
        User user = userRepository.findByUsernameAndPassword(studentRepository.get(id).getUsername(), studentRepository.get(id).getPassword());
        List<Course> courseList = courseRepository.getAll();
        List<UserCourse> userCourseList = userCourseRepository.getAll();
        List<Enrollment> enrollmentList = new ArrayList<>();
        Enrollment enrollment;
        for (int i = 0; i < userCourseList.size(); i++) {
            if (userCourseList.get(i).getUserId() == user.getId()) {
                for (int j = 0; j < courseList.size(); j++) {
                    if (userCourseList.get(i).getCourseId() == courseList.get(j).getId()) {
                        enrollment = new Enrollment(courseList.get(j), userCourseList.get(i).getGrade());
                        enrollmentList.add(enrollment);
                    }
                }
            }
        }

        return enrollmentList;
    }
}
