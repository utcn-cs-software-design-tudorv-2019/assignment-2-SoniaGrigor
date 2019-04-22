package model.business.course;

import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;
import model.persistence.entity.User;
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.user.UserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CourseServicePostgreSQL implements CourseService {

    @Inject
    private CourseRepository courseRepository;

    @Inject
    private UserRepository userRepository;

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
    public void removeAll() {
        courseRepository.deleteAll();
    }

    @Override
    public List<Enrollment> getMyCourses(int id) {
        List<Enrollment> enrollmentList = null;
        User user = userRepository.get(id);
        List<Course> allCourses = courseRepository.getAll();
        //something else
        return enrollmentList;
    }

    @Override
    public ArrayList<Integer> getIdByName(ArrayList<String> selectedCourses) {
        ArrayList<Integer> idCourses = new ArrayList<>();
        for (String selectedCourse : selectedCourses) {
            int idCourse = getIdByName(selectedCourse);
            idCourses.add(idCourse);
        }

        return idCourses;
    }

    @Override
    public int getIdByName(String courseName) {
        int id = 0;
        //something else
        return id;
    }
}
