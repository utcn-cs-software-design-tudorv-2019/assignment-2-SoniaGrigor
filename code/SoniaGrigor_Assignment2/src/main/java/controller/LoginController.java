package controller;

import javafx.scene.control.Alert;
import model.business.course.CourseService;
import model.business.student.StudentService;
import model.business.user.AuthenticationService;
import model.business.user.UserService;
import model.persistence.entity.User;
import model.persistence.entity.validation.Notification;
import model.persistence.my_utility.Utility;
import model.persistence.repository.user.AuthenticationException;
import view.RegisterView;
import view.StudentView;
import view.UserView;

import java.io.FileNotFoundException;

import static model.persistence.my_utility.ProjectConstants.LOGIN_FAIL;
import static model.persistence.my_utility.ProjectConstants.LOGIN_FAIL_MESSAGE;

public class LoginController {

    public static void handleLoginButtonEvent(String username, String password, AuthenticationService authenticationService, CourseService courseService, StudentService studentService, UserService userService) {

        UserView userView;
        StudentView studentView;

        Notification<User> loginNotification = null;
        try {
            loginNotification = authenticationService.login(username, password);
        } catch (AuthenticationException e1) {
            e1.printStackTrace();
        }

        if (loginNotification != null) {
            if (loginNotification.hasErrors()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(LOGIN_FAIL);
                alert.setHeaderText(LOGIN_FAIL_MESSAGE);
                alert.setContentText(loginNotification.getFormattedErrors());
                alert.showAndWait();
            } else {
                if (Utility.getUserRole(username.toLowerCase()) == 2)
                    studentView = new StudentView(authenticationService, courseService, studentService, userService);
                else {
                    userView = new UserView(authenticationService, courseService, studentService, userService);
                }
            }
        }
    }

    public static void handleRegisterButtonEvent(AuthenticationService authenticationService, CourseService courseService, StudentService studentService, UserService userService) {
        try {
            new RegisterView(authenticationService, courseService, studentService, userService);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
