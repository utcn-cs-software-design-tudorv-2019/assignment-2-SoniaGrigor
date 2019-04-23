package model.persistence.repository.course;

import model.persistence.entity.Course;

import java.util.List;

public interface CourseRepository {

    List<Course> getAll();

    Course get(int id);

    boolean save(Course course);

    boolean update(Course course);

    boolean delete(Course course);
}
