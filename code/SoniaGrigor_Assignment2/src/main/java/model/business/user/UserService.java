package model.business.user;

import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;
import model.persistence.repository.user.AuthenticationException;

import java.util.List;

public interface UserService {
    List<User> findAll();

    Notification<User> findByUsernameAndPassword(String username, String password) throws AuthenticationException;

    boolean update(User user);

    boolean save(User user);

    boolean removeAll();

    boolean removeById(int id);

    User findById(int idUser);

    void generateRaport();
}
