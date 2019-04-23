package model.business.course;

import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;

import java.util.List;

public interface CourseService {
    List<Course> findAll();

    boolean save(Course course);

    List<Enrollment> getMyCourses(int id);
}
