package model.business.user;

import model.persistence.entity.Role;
import model.persistence.entity.User;
import model.persistence.entity.builder.UserBuilder;
import model.persistence.entity.validation.Notification;
import model.persistence.entity.validation.UserValidator;
import model.persistence.my_utility.Utility;
import model.persistence.repository.security.RightsRolesRepository;
import model.persistence.repository.user.UserRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import static model.persistence.my_utility.DBConstants.Roles.BASIC;

public class AuthenticationServicePostgreSQL implements AuthenticationService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private RightsRolesRepository rightsRolesRepository;

    public AuthenticationServicePostgreSQL() {

    }

    public static String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public Notification<Boolean> register(String name, String username, String password, String email, String cnp, String role) {
        Role basicRole = rightsRolesRepository.findRoleByTitle(BASIC);
        User user = new UserBuilder()
                .setName(name)
                .setUsername(username)
                .setPassword(password)
                .setEmail(email)
                .setCNP(cnp)
                .build();

        UserValidator userValidator = new UserValidator(user);
        boolean userValid = userValidator.validate();
        Notification<Boolean> userRegisterNotification = new Notification<>();

        if (!userValid) {
            userValidator.getErrors().forEach(userRegisterNotification::addError);
            userRegisterNotification.setResult(Boolean.FALSE);
        } else {
            user.setPassword(encodePassword(password));
            userRegisterNotification.setResult(userRepository.save(user));
        }
        return userRegisterNotification;
    }

    @Override
    public boolean login(String username, String password) throws IOException, IndexOutOfBoundsException {
        User user = userRepository.findByUsernameAndPassword(username, encodePassword(password));
        Utility.setLoggedUser(user.getId());

        return user != null;
    }

    @Override
    public boolean logout(User user) {
        return false;
    }

    @Override
    public int getLastIndex() {
        return userRepository.getLastIndex();
    }
}