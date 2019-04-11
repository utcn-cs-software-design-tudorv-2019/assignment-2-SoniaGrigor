package model.business.course;

import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;
import model.persistence.repository.course.CourseRepository;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class CourseServicePostgreSQL implements CourseService {
    private final SessionFactory sessionFactory;

    private CourseRepository courseRepository;

    public CourseServicePostgreSQL(SessionFactory sessionFactory, CourseRepository courseRepository) {
        this.sessionFactory = sessionFactory;
        this.courseRepository = courseRepository;
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
        int id=0;
        //something else
        return id;
    }
}
