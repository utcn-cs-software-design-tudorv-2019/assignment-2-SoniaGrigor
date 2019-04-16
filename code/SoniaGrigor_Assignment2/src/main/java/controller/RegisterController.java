package controller;

import javafx.scene.control.Alert;
import model.business.course.CourseService;
import model.business.student.StudentService;
import model.business.user.AuthenticationService;
import model.business.user.UserService;
import model.persistence.entity.Student;
import model.persistence.entity.builder.StudentBuilder;
import model.persistence.entity.validation.Notification;
import view.LoginView;

import java.io.FileNotFoundException;

import static model.business.user.AuthenticationServicePostgreSQL.encodePassword;
import static model.persistence.my_utility.ProjectConstants.*;

public class RegisterController {
    public static void handleRegisterButtonEvent(String name, String username, String password, String email, String cnp, String role,
                                                 AuthenticationService authenticationService, CourseService courseService, StudentService studentService, UserService userService) {
        Notification<Boolean> registerNotification = authenticationService.register(name, username, password, email, cnp , role);
        if(role.equals("BASIC")) {
            Student student= new StudentBuilder()
                    .setName(name)
                    .setUsername(username)
                    .setPassword(encodePassword(password))
                    .setEmail(email)
                    .setCNP(cnp)
                    .setCardNo(0)
                    .setGroup(0)
                    .build();
            studentService.save(student);
        }
        if (registerNotification != null) {
            if (registerNotification.hasErrors()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(REGISTER_FAIL);
                alert.setHeaderText(REGISTER_FAIL_MESSAGE);
                alert.setContentText(registerNotification.getFormattedErrors());
                alert.showAndWait();

            }else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(REGISTER_SUCCES);
                alert.setHeaderText(REGISTER_SUCCES);
                alert.setContentText(registerNotification.getFormattedErrors());
                alert.showAndWait();
                try {
                    new LoginView(authenticationService,courseService,studentService,userService ).usernameField.setText(username);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }



    }
}
