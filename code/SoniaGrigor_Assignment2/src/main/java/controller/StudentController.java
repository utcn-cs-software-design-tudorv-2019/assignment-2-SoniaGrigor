package controller;

import javafx.scene.control.Alert;
import model.business.course.CourseService;
import model.business.student.StudentService;
import model.business.user.AuthenticationService;
import model.business.user.UserService;
import model.persistence.entity.Student;
import model.persistence.entity.builder.StudentBuilder;
import model.persistence.my_utility.Utility;
import view.LoginView;

import java.io.FileNotFoundException;

import static model.business.user.AuthenticationServicePostgreSQL.encodePassword;
import static model.persistence.my_utility.ProjectConstants.PASSWORD_TITLE;
import static model.persistence.my_utility.ProjectConstants.PASWORD_MESSAGE;

public class StudentController {

    public static Student student;

    public static void handleUpdateButtonEvent(StudentService studentService, String name, String username, String password, String email, String cnp, int cardNo, int group) {

        if (password.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(PASSWORD_TITLE);
            alert.setHeaderText(PASWORD_MESSAGE);
            alert.showAndWait();
        } else {
            student = (Student) new StudentBuilder()
                    .setId(Utility.getLoggedUser())
                    .setGroup(group)
                    .setCardNo(cardNo)
                    .setName(name)
                    .setUsername(username)
                    .setPassword(encodePassword(password))
                    .setEmail(email)
                    .setCNP(cnp)
                    .build();
            studentService.update(student);
        }

    }

    public static void handleDeleteButtonEvent(AuthenticationService authenticationService, StudentService studentService, UserService userService, CourseService courseService) {
        studentService.removeById(Utility.getLoggedUser());
        try {
            new LoginView(authenticationService, courseService, studentService, userService);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
