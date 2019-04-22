package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.scene.control.Alert;
import model.business.user.UserService;
import model.persistence.entity.User;
import model.persistence.entity.builder.UserBuilder;
import model.persistence.my_utility.GuiceModule;
import model.persistence.my_utility.Utility;
import view.LoginView;

import javax.inject.Inject;
import java.io.FileNotFoundException;

import static model.business.user.AuthenticationServicePostgreSQL.encodePassword;
import static model.persistence.my_utility.ProjectConstants.PASSWORD_TITLE;
import static model.persistence.my_utility.ProjectConstants.PASWORD_MESSAGE;

public class AdminController {
    public static User user;
    private static Injector injector = Guice.createInjector(new GuiceModule());
    @Inject
    private static UserService userService = injector.getInstance(UserService.class);

    public static void handleUpdateButtonEvent(UserService userService, String name, String username, String password, String email, String cnp) {

        if (password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(PASSWORD_TITLE);
            alert.setHeaderText(PASWORD_MESSAGE);
            alert.showAndWait();
        } else {
            user = new UserBuilder()
                    .setId(Utility.getLoggedUser())
                    .setName(name)
                    .setUsername(username)
                    .setPassword(encodePassword(password))
                    .setEmail(email)
                    .setCNP(cnp)
                    .build();
            userService.update(user);
        }

    }

    public static void handleDeleteButtonEvent() {
        userService.removeById(Utility.getLoggedUser());
        try {
            new LoginView();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
