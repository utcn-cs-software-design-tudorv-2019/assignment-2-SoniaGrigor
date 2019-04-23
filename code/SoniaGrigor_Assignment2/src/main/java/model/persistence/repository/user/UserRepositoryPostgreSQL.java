package model.persistence.repository.user;

import model.persistence.entity.User;
import model.persistence.my_utility.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class UserRepositoryPostgreSQL implements UserRepository {

    private Session session;

    public UserRepositoryPostgreSQL() {
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws IndexOutOfBoundsException {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        User user = (User) session.createCriteria(User.class).add(Restrictions.ilike("username", username)).add(Restrictions.ilike("password", password)).list().get(0);
        session.getTransaction().commit();
        session.close();
        return user;
    }

    @Override
    public boolean update(User user) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(User user) {
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        User user = get(id);
        session = HibernateUtil.getSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public int getLastIndex() {
        return 0;
    }

    @Override
    public User get(int idUser) {
        try {
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class).add(Restrictions.eq("id", idUser));
            User user = (User) criteria.list().get(0);
            session.getTransaction().commit();
            session.close();
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
