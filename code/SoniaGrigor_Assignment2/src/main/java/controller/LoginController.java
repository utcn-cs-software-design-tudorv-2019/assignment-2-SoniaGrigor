package controller;

import com.google.inject.Guice;
import com.google.inject.Injector;
import javafx.scene.control.Alert;
import model.business.student.StudentService;
import model.business.user.AuthenticationService;
import model.persistence.my_utility.GuiceModule;
import model.persistence.my_utility.UtilityAuthorization;
import model.persistence.repository.user.AuthenticationException;
import view.RegisterView;
import view.StudentView;
import view.UserView;

import javax.inject.Inject;
import java.io.FileNotFoundException;
import java.io.IOException;

import static model.persistence.my_utility.ProjectConstants.LOGIN_FAIL;
import static model.persistence.my_utility.ProjectConstants.LOGIN_FAIL_MESSAGE;

public class LoginController {
    public static Injector injector = Guice.createInjector(new GuiceModule());

    @Inject
    private static AuthenticationService authenticationService = injector.getInstance(AuthenticationService.class);
    @Inject
    private static StudentService studentService = injector.getInstance(StudentService.class);

    public static void handleLoginButtonEvent(String username, String password) {

        UserView userView;
        StudentView studentView;

        boolean loginNotification = false;
        try {
            if (UtilityAuthorization.getUserRole(username.toLowerCase()) == 2) {
                loginNotification = studentService.login(username, password);
            } else {
                loginNotification = authenticationService.login(username, password);
            }

        } catch (AuthenticationException e1) {
            e1.printStackTrace();
            loginNotification = false;
        } catch (IOException e) {
            e.printStackTrace();
            loginNotification = false;
        } catch (IndexOutOfBoundsException e2) {
            e2.printStackTrace();
            loginNotification = false;
        }

        if (loginNotification == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(LOGIN_FAIL);
            alert.setHeaderText(LOGIN_FAIL_MESSAGE);
            alert.setContentText("Unable to login!");
            alert.showAndWait();
        } else {
            if (UtilityAuthorization.getUserRole(username.toLowerCase()) == 2)
                studentView = new StudentView();
            else {
                userView = new UserView();
            }
        }

    }

    public static void handleRegisterButtonEvent() {
        try {
            new RegisterView();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
