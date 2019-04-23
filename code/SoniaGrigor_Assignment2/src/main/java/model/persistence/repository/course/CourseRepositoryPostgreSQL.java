package model.persistence.repository.course;

import model.persistence.entity.Course;
import model.persistence.my_utility.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class CourseRepositoryPostgreSQL implements CourseRepository {

    private Session session;

    public CourseRepositoryPostgreSQL() {
    }

    @Override
    public List<Course> getAll() {
        try {
            session = HibernateUtil.getSession();
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
            session = HibernateUtil.getSession();
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
            session = HibernateUtil.getSession();
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
    public boolean update(Course course) {
        try {
            session = HibernateUtil.getSession();
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
            session = HibernateUtil.getSession();
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
