package model.business.course;

import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;

import java.util.ArrayList;
import java.util.List;

public interface CourseService {
    List<Course> findAll();

    boolean save(Course course);

    void removeAll();

    List<Enrollment> getMyCourses(int id);

    ArrayList<Integer> getIdByName(ArrayList<String> selectedCourses);

    int getIdByName(String course);
}
