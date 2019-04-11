package model.persistence.repository.user;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;
import model.persistence.repository.security.RightsRolesRepository;

import javax.inject.Inject;
import java.util.List;

public class UserRepositoryPostgreSQL implements UserRepository {

    private final SessionFactory sessionFactory;
    @Inject
    private RightsRolesRepository rightsRolesRepository;
    private Session session;

    public UserRepositoryPostgreSQL(SessionFactory sessionFactory ) {
        this.sessionFactory = sessionFactory;
        this.session=sessionFactory.openSession();
    }


    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public boolean save(User user) {
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
        return false;
    }

    @Override
    public int getLastIndex() {
        return 0;
    }

    @Override
    public User get(int idUser) {
        return null;
    }

    @Override
    public void generateRaport() {

    }
}
