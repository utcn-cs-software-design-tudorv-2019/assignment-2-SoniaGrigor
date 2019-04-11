package model.business.user;

import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;
import model.persistence.repository.user.AuthenticationException;

public interface AuthenticationService {

    Notification<Boolean> register(String name, String username, String password, String email, String cnp);
    Notification<User> login(String username, String password) throws AuthenticationException;
    boolean logout(User user);
    int getLastIndex();
}