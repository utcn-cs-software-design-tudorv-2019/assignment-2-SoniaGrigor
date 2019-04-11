package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.business.course.CourseService;
import model.business.student.StudentService;
import model.business.user.AuthenticationService;
import model.business.user.UserService;

import java.io.FileNotFoundException;

import static model.persistence.my_utility.ProjectConstants.LOGIN_TITLE;

public class LoginView {
    Stage window;
    private final AuthenticationService authenticationService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final UserService userService;

    public TextField usernameField;
    private PasswordField passwordField;
    private Label registerLabel;
    private Button loginButton;
    private Button registerButton;


    public LoginView(AuthenticationService authenticationService, CourseService courseService, StudentService studentService, UserService userService) throws FileNotFoundException {

        window = new Stage();
        window.setTitle(LOGIN_TITLE);

        this.authenticationService = authenticationService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.userService=userService;

        BorderPane layout = new BorderPane();
        layout.setId("root");
        VBox topPane = new VBox(40);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(100, 20, 20, 20));


        VBox leftPane = new VBox(40);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(20, 20, 20, 400));
        Label label1 = new Label("Username");
        Label label2 = new Label("Password");
        leftPane.getChildren().addAll(label1, label2);

        VBox rightPane = new VBox(30);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(20, 400, 20, 20));
        usernameField = new TextField();
        usernameField.setMinWidth(300);
        passwordField = new PasswordField();
        passwordField.setMinWidth(300);

        rightPane.getChildren().addAll(usernameField, passwordField);

        VBox bottomPane = new VBox(30);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(20, 20, 100, 20));
        loginButton = new Button("Login");
        loginButton.setOnAction(e -> LoginController.handleLoginButtonEvent(usernameField.getText(),passwordField.getText(), authenticationService, courseService, studentService, userService ));
        registerButton = new Button("Register");
        registerButton.setOnAction(e -> LoginController.handleRegisterButtonEvent(authenticationService,courseService,studentService,userService));
        registerLabel = new Label("If you do not have an account, press register.");
        bottomPane.getChildren().addAll(loginButton, registerLabel, registerButton);

        layout.setTop(topPane);
        layout.setLeft(leftPane);
        layout.setRight(rightPane);
        layout.setBottom(bottomPane);
        Scene scene = new Scene(layout, 1200, 800);
        window.setScene(scene);
        window.show();
    }

}

