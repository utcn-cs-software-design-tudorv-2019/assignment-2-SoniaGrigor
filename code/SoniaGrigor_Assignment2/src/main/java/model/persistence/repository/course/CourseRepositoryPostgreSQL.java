package model.persistence.repository.course;

import model.persistence.entity.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CourseRepositoryPostgreSQL implements CourseRepository {

    private final SessionFactory sessionFactory;
    private Session session;

    public CourseRepositoryPostgreSQL(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Course> getAll() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Course> courseList = session.createCriteria(Course.class).list();
            session.getTransaction().commit();
            session.close();
            return courseList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Course get(int id) {
        Course course;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            course = (Course) session.get(Course.class, id);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return course;
    }

    @Override
    public boolean save(Course course) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(course);
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteAll() {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            List<Course> courseList = getAll();
            courseList.forEach(course -> session.delete(course));
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Course course) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(course);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Course course) {
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.delete(course);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
