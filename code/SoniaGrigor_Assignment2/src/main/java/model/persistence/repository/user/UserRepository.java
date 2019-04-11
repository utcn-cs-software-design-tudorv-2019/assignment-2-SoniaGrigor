package model.persistence.repository.user;

import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;

import java.util.List;

public interface UserRepository {

    List<User> getAll();
    Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException;
    boolean update(User user);
    boolean save(User user);
    boolean deleteAll();
    boolean deleteById(int id);
    int getLastIndex();
    User get(int id);

    void generateRaport();
}
