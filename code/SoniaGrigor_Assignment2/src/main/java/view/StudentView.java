package view;

import com.google.inject.Guice;
import com.google.inject.Injector;
import controller.StudentController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.business.course.CourseService;
import model.business.student.StudentService;
import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;
import model.persistence.entity.Student;
import model.persistence.my_utility.GuiceModule;
import model.persistence.my_utility.UtilityAuthorization;

import javax.inject.Inject;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static model.persistence.my_utility.ProjectConstants.*;

public class StudentView {

    public static Injector injector = Guice.createInjector(new GuiceModule());

    @Inject
    private static StudentService studentService = injector.getInstance(StudentService.class);

    @Inject
    private static CourseService courseService = injector.getInstance(CourseService.class);
    private static Student student;
    Stage window;
    Scene sceneMain;
    int idUser = UtilityAuthorization.getLoggedUser();

    private TextField nameField;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField emailField;
    private TextField cnpField;
    private TextField cardNoField;
    private TextField groupField;
    private ArrayList<Course> courseList;
    private ChoiceBox courseListView;

    private Button updateButton;
    private Button deleteButton;
    private Button viewCoursesButton;

    public StudentView() {
        window = new Stage();
        window.setTitle(STUDENT_TITLE);

        student = studentService.findById(idUser);

        BorderPane layout = new BorderPane();
        layout.setId("root");
        VBox topPane = new VBox(40);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(100, 20, 20, 20));


        VBox leftPane = new VBox(40);
        leftPane.setAlignment(Pos.CENTER_RIGHT);
        leftPane.setPadding(new Insets(20, 20, 20, 400));
        Label labelName = new Label("Name");
        Label labelUsername = new Label("Username");
        Label labelPassword = new Label("Password");
        Label labelEmail = new Label("Email");
        Label labelCNP = new Label("CNP");
        Label labelCourse = new Label("Courses");
        Label labelCardNo = new Label("Card Number");
        Label labelGroup = new Label("Group");
        //leftPane.getChildren().addAll(labelName, labelUsername, labelPassword, labelEmail, labelCNP, labelCourse, labelCardNo, labelGroup);
        leftPane.getChildren().addAll(labelName, labelUsername, labelPassword, labelEmail, labelCNP, labelCardNo, labelGroup);

        VBox rightPane = new VBox(30);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(20, 400, 20, 20));
        nameField = new TextField(student.getName());
        nameField.setMinWidth(300);
        usernameField = new TextField(student.getUsername());
        usernameField.setMinWidth(300);
        passwordField = new PasswordField();
        passwordField.setMinWidth(300);
        emailField = new TextField(student.getEmail());
        emailField.setMinWidth(300);
        cnpField = new TextField(student.getCNP());
        cnpField.setMinWidth(300);
        cardNoField = new TextField(Integer.toString(student.getCardNo()));
        cardNoField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cardNoField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        cardNoField.setMinWidth(300);
        groupField = new TextField(Integer.toString(student.getGroup()));
        groupField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    cardNoField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        groupField.setMinWidth(300);

        courseListView = new ChoiceBox();
        courseListView.setMinWidth(300);
        courseList = new ArrayList<>(courseService.findAll());
        List<String> c = courseList.stream().map(Course::getName).collect(Collectors.toList());
        courseListView.getItems().setAll(c);
        rightPane.getChildren().addAll(nameField, usernameField, passwordField, emailField, cnpField, cardNoField, groupField);

        VBox bottomPane = new VBox(30);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(20, 20, 100, 20));
        updateButton = new Button("Update Information");

        updateButton.setOnAction(e -> StudentController.handleUpdateButtonEvent(nameField.getText(), usernameField.getText(), passwordField.getText(), emailField.getText(),
                cnpField.getText(), Integer.parseInt(cardNoField.getText()), Integer.parseInt(groupField.getText())));
        deleteButton = new Button("Delete Accout");
        deleteButton.setOnAction(e -> StudentController.handleDeleteButtonEvent());
        viewCoursesButton = new Button("View Courses");
        viewCoursesButton.setOnAction(e -> handleViewCoursesButtonEvent());
        bottomPane.getChildren().addAll(updateButton, deleteButton, viewCoursesButton);

        layout.setTop(topPane);
        layout.setLeft(leftPane);
        layout.setRight(rightPane);
        layout.setBottom(bottomPane);

        sceneMain = new Scene(layout, 1200, 800);
        window.setScene(sceneMain);
        window.show();
    }

    private void handleViewCoursesButtonEvent() {
        try {
            List<Enrollment> myCourseList = courseService.getMyCourses(UtilityAuthorization.getLoggedUser());
            ObservableList<Enrollment> data = FXCollections.observableList(myCourseList);

            TableView<Enrollment> table = new TableView<>();

            TableColumn nameCol = new TableColumn("Name");
            nameCol.setMinWidth(100);
            nameCol.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("courseName"));

            TableColumn creditCol = new TableColumn("Credit");
            creditCol.setMinWidth(100);
            creditCol.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("creditCourse"));

            TableColumn examCol = new TableColumn("Exam Date");
            examCol.setMinWidth(200);
            examCol.setCellValueFactory(new PropertyValueFactory<Enrollment, Date>("dateExam"));

            TableColumn roomCol = new TableColumn("Room");
            roomCol.setMinWidth(200);
            roomCol.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("roomCourse"));

            TableColumn gradeCol = new TableColumn("Grade");
            gradeCol.setMinWidth(200);
            gradeCol.setCellValueFactory(new PropertyValueFactory<Enrollment, Integer>("grade"));

            table.setItems(data);
            table.getColumns().addAll(nameCol, creditCol, examCol, roomCol, gradeCol);

            HBox hbox = new HBox();
            hbox.setSpacing(5);
            hbox.setPadding(new Insets(10, 0, 0, 10));
            hbox.getChildren().addAll(table);

            VBox vBox = new VBox();
            vBox.setSpacing(5);
            vBox.setPadding(new Insets(10, 0, 0, 10));
            vBox.getChildren().addAll(hbox);

            Scene scene = new Scene(vBox, 1200, 800);

            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(NO_COURSES_TITLE);
            alert.setHeaderText(NO_COURSES_MESSAGE);
            alert.showAndWait();
        }

    }

}
