package view;

import controller.UserController;
import model.business.course.CourseService;
import model.business.student.StudentService;
import model.business.user.AuthenticationService;
import model.business.user.UserService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.persistence.entity.User;
import model.persistence.my_utility.Utility;

import static model.persistence.my_utility.ProjectConstants.TEACHER_TITLE;

public class UserView {

    Stage window;
    Scene sceneMain;

    private AuthenticationService authenticationService;
    private StudentService studentService;
    private CourseService courseService;
    private UserService userService;

    int idUser = Utility.getLoggedUser();
    private User user;

    private Button basicOperationButton;
    private Button specificOperationButton;

    public UserView(AuthenticationService authenticationService, CourseService courseService, StudentService studentService, UserService userService) {
        window = new Stage();
        window.setTitle(TEACHER_TITLE);

        this.authenticationService = authenticationService;
        this.courseService = courseService;
        this.studentService = studentService;
        this.userService=userService;

        user = userService.findById(idUser);

        BorderPane layout = new BorderPane();
        layout.setId("root");
        VBox topPane = new VBox(40);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new javafx.geometry.Insets(100, 20, 20, 20));


        VBox leftPane = new VBox(40);
        leftPane.setAlignment(Pos.CENTER_RIGHT);
        leftPane.setPadding(new Insets(20, 20, 20, 400));
        basicOperationButton = new Button("Basic Operations");
        basicOperationButton.setOnAction(e->UserController.handleBasicOperationButtonEvent(authenticationService, courseService,studentService,userService));
        leftPane.getChildren().addAll(basicOperationButton);


        VBox rightPane = new VBox(30);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(20, 400, 20, 20));
        specificOperationButton = new Button("Specific Operations");
        specificOperationButton.setOnAction(e->UserController.handleSpecificOperationButtonEvent(authenticationService, courseService,studentService,userService));
        rightPane.getChildren().addAll(specificOperationButton);


        VBox bottomPane = new VBox(30);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(20, 20, 100, 20));

        layout.setTop(topPane);
        layout.setLeft(leftPane);
        layout.setRight(rightPane);
        layout.setBottom(bottomPane);

        sceneMain = new Scene(layout, 1200, 800);
        window.setScene(sceneMain);
        window.show();
    }

}
