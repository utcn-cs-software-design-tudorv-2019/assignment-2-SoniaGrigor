package model.business.user;

import model.business.security.RightsRolesService;
import org.hibernate.SessionFactory;
import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;
import model.persistence.repository.user.AuthenticationException;
import model.persistence.repository.user.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserServicePostgreSQL implements UserService {
    private final SessionFactory sessionFactory;
    @Inject
    private RightsRolesService rightsRolesService;
    @Inject
    private UserRepository userRepository;

    public UserServicePostgreSQL(SessionFactory sessionFactory) {
        this.sessionFactory=sessionFactory;
    }

    @Override
    public List<User> findAll() {
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
        return false;
    }

    @Override
    public boolean removeAll() {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }

    @Override
    public User findById(int idUser) {
        return userRepository.get(idUser);
    }

    @Override
    public void generateRaport() {
        userRepository.generateRaport();
    }
}
