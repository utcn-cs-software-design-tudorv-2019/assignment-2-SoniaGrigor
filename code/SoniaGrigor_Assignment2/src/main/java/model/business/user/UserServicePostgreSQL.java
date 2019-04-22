package model.business.user;

import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;
import model.persistence.repository.user.AuthenticationException;
import model.persistence.repository.user.UserRepository;

import javax.inject.Inject;
import java.util.List;

public class UserServicePostgreSQL implements UserService {

    @Inject
    private UserRepository userRepository;

    public UserServicePostgreSQL() {
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

        return userRepository.update(user);
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

        return userRepository.deleteById(id);
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
