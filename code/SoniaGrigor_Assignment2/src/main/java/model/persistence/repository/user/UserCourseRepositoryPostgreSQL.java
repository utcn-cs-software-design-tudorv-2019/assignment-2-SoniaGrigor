package model.persistence.repository.user;

import model.persistence.entity.UserCourse;
import model.persistence.my_utility.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserCourseRepositoryPostgreSQL implements UserCourseRepository {

    private Session session;

    @Override
    public List<UserCourse> getAll() {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            List<UserCourse> userCourseList = session.createCriteria(UserCourse.class).list();
            session.getTransaction().commit();
            session.close();

            return userCourseList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean update(UserCourse userCourse) {
        return false;
    }

    @Override
    public boolean save(UserCourse userCourse) {
        return false;
    }

    @Override
    public UserCourse get(int id) {
        return null;
    }

    @Override
    public UserCourse getByDetails(int idUser, int idCourse) {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(UserCourse.class).add(Restrictions.eq("userId", idUser)).add(Restrictions.eq("courseId", idCourse));
            UserCourse userCourse = (UserCourse) criteria.list().get(0);
            session.getTransaction().commit();
            session.close();
            return userCourse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
