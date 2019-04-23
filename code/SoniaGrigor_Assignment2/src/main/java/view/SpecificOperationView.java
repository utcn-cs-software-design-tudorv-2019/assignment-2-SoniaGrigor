package view;

import controller.SpecificOperationController;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.persistence.entity.Course;
import model.persistence.entity.Enrollment;
import model.persistence.entity.Student;
import model.persistence.entity.User;
import model.persistence.my_utility.UtilityAuthorization;

import static model.persistence.my_utility.ProjectConstants.TEACHER_TITLE;

public class SpecificOperationView {

    Stage window;
    Scene sceneMain;

    int idUser = UtilityAuthorization.getLoggedUser();
    private User user;

    private Button enrollStudentButton;
    private Button viewStudentsButton;
    private Button gradeStudentButton;
    private Button viewEnrollCoursesButton;
    private Button viewAllCoursesButton;
    private Button groupStudentButton;
    private Button generateRaport;

    private TextField gradeField;
    private Label gradeLabel;
    private TextField groupField;
    private Label groupLabel;
    private Label instructions;

    private TableView<Student> studentTable = new TableView<Student>();
    private TableView<Enrollment> courseTable = new TableView<Enrollment>();
    private TableView<Course> allCourseTable = new TableView<Course>();


    public SpecificOperationView() {
        window = new Stage();
        window.setTitle(TEACHER_TITLE);

        BorderPane layout = new BorderPane();
        layout.setId("root");
        HBox topPane = new HBox(10);
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(new Insets(10, 10, 0, 10));
        topPane.getChildren().addAll();


        VBox leftPane = new VBox(10);
        leftPane.setAlignment(Pos.CENTER);
        leftPane.setPadding(new Insets(0, 0, 10, 20));
        instructions = new Label("\nPlease press buttons in order to see data.\nPlease select data from table.");
        viewStudentsButton = new Button("View students");
        viewStudentsButton.setOnAction(e -> SpecificOperationController.handleViewStudentsButtonEvent(studentTable));
        groupLabel = new Label("Enter a new group");
        groupField = new TextField();
        groupField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    groupField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        groupStudentButton = new Button("Set new group");
        groupStudentButton.setOnAction(e -> SpecificOperationController.handleGroupStudentButtonEvent(Integer.parseInt(groupField.getText()), studentTable.getSelectionModel().getSelectedItem().getId()));

        viewAllCoursesButton = new Button("View All Courses");
        viewAllCoursesButton.setOnAction(e -> SpecificOperationController.handleViewAllCoursesButtonEvent(allCourseTable));
        enrollStudentButton = new Button("Enroll student");
        enrollStudentButton.setOnAction(e -> SpecificOperationController.handleEnrollStudentButtonEvent(studentTable.getSelectionModel().getSelectedItem().getId(), allCourseTable.getSelectionModel().getSelectedItem().getId()));
        leftPane.getChildren().addAll(instructions, viewStudentsButton, studentTable, groupLabel, groupField, groupStudentButton, viewAllCoursesButton, allCourseTable, enrollStudentButton);

        VBox rightPane = new VBox(10);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(0, 400, 0, 10));
        viewEnrollCoursesButton = new Button("View Enroll Courses");
        viewEnrollCoursesButton.setOnAction(e -> SpecificOperationController.handleViewEnrollCoursesButtonEvent(courseTable, studentTable));
        gradeLabel = new Label("Enter a new grade ");
        gradeField = new TextField();
        gradeStudentButton = new Button("Set new grade");
        gradeStudentButton.setOnAction(e -> SpecificOperationController.handleGradeStudentButtonEvent(courseTable, studentTable, Integer.parseInt(gradeField.getText())));
        rightPane.getChildren().addAll(viewEnrollCoursesButton, courseTable, gradeLabel, gradeField, gradeStudentButton);

        VBox bottomPane = new VBox(10);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(0, 10, 10, 10));
        generateRaport = new Button("Generate raport");
        generateRaport.setOnAction(e -> SpecificOperationController.handleGenerateRaportButtonEvent(studentTable));
        bottomPane.getChildren().addAll(generateRaport);

        layout.setTop(topPane);
        layout.setLeft(leftPane);
        layout.setRight(rightPane);
        layout.setBottom(bottomPane);

        sceneMain = new Scene(layout, 1200, 800);
        window.setScene(sceneMain);
        window.show();
    }

}
