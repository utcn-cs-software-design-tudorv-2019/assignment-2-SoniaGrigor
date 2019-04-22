package model.business.user;

import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;
import model.persistence.repository.user.AuthenticationException;

import java.io.IOException;

public interface AuthenticationService {

    Notification<Boolean> register(String name, String username, String password, String email, String cnp, String role);

    boolean login(String username, String password) throws AuthenticationException, IOException;

    boolean logout(User user);

    int getLastIndex();
}