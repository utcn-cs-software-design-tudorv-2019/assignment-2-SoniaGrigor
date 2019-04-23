package model.persistence.repository.student;

import model.persistence.entity.Student;
import model.persistence.entity.UserCourse;
import model.persistence.my_utility.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class StudentRepositoryPostgreSQL implements StudentRepository {

    private Session session;

    public StudentRepositoryPostgreSQL() {
    }

    @Override
    public List<Student> getAll() {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            List<Student> studentList = session.createCriteria(Student.class).list();
            session.getTransaction().commit();
            session.close();

            return studentList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(Student student) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(Student user) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteById(int id) {
        Student student = getStudentInfo(id);
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Student student) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(student);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean enrollCourse(UserCourse userCourse) {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.save(userCourse);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Student get(int id) {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(Student.class).add(Restrictions.eq("id", id));
            Student user = (Student) criteria.list().get(0);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean updateGrade(UserCourse userCourse) {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.update(userCourse);
            session.getTransaction().commit();
            session.close();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateGroup(int idStudent, int group) {
        Student student = getStudentInfo(idStudent);
        student.setGroup(group);
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(student);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public Student getStudentInfo(int id) {

        session = HibernateUtil.getSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Student.class).add(Restrictions.eq("id", id));
        Student student = (Student) criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return student;
    }

    @Override
    public Student findByUsernameAndPassword(String username, String encodePassword) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        Student student = (Student) session.createCriteria(Student.class).add(Restrictions.ilike("username", username)).add(Restrictions.ilike("password", encodePassword)).list().get(0);
        session.getTransaction().commit();
        session.close();
        return student;
    }
}
