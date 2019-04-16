package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.business.course.CourseService;
import model.business.student.StudentService;
import model.business.user.UserService;
import model.persistence.entity.*;
import model.persistence.entity.Course;

import java.sql.Date;
import java.util.List;

import static model.persistence.my_utility.ProjectConstants.*;

public class SpecificOperationController {

    public static void handleGenerateRaportButtonEvent(UserService userService) {
        userService.generateRaport();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(REPORT_TITLE);
        alert.setHeaderText(REPORT_MESSAGE);
        alert.showAndWait();
    }

    public static void handleGroupStudentButtonEvent(StudentService studentService, int group, int idStudent) {
        if (!studentService.updateGroup(idStudent, group)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(OPERATION_FAILED);
            alert.setHeaderText(OPERATION_FAILED_MESSAGE);
            alert.showAndWait();
        }
    }

    public static void handleViewAllCoursesButtonEvent(CourseService courseService, TableView<Course> allCourseTable) {
        allCourseTable.getColumns().clear();

        List<Course> myCourseList = courseService.findAll();
        ObservableList<Course> data = FXCollections.observableList(myCourseList);

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<Course, String>("name"));

        TableColumn creditCol = new TableColumn("Credit");
        creditCol.setMinWidth(100);
        creditCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("credit"));

        TableColumn examCol = new TableColumn("Exam Date");
        examCol.setMinWidth(200);
        examCol.setCellValueFactory(new PropertyValueFactory<Course, Date>("exam"));

        TableColumn roomCol = new TableColumn("Room");
        roomCol.setMinWidth(200);
        roomCol.setCellValueFactory(new PropertyValueFactory<Course, String>("room"));

        allCourseTable.setItems(data);
        allCourseTable.getColumns().addAll(nameCol, creditCol, examCol, roomCol);
    }

    public static void handleViewEnrollCoursesButtonEvent(CourseService courseService, TableView<Enrollment> courseTable, TableView<StudentPersonalInfo> studentTable) {
        courseTable.getColumns().clear();
        List<Enrollment> myCourseList = courseService.getMyCourses(studentTable.getSelectionModel().getSelectedItem().getId());
        ObservableList<Enrollment> data = FXCollections.observableList(myCourseList);

        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<Enrollment, String>("courseId"));

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

        courseTable.setItems(data);
        courseTable.getColumns().addAll(idCol, nameCol, creditCol, examCol, roomCol, gradeCol);

    }

    public static void handleGradeStudentButtonEvent(StudentService studentService,TableView<Enrollment> courseTable, TableView<StudentPersonalInfo> studentTable, int grade ) {
        int idStudent = studentTable.getSelectionModel().getSelectedItem().getId();
        int idCourse = courseTable.getSelectionModel().getSelectedItem().getCourseId();
        studentService.updateGrade(idStudent, idCourse, grade);
    }

    public static void handleViewStudentsButtonEvent(StudentService studentService,  TableView<StudentPersonalInfo> studentTable) {
        studentTable.getColumns().clear();

        List<StudentPersonalInfo> studentList = studentService.findAll();
        ObservableList<StudentPersonalInfo> data = FXCollections.observableList(studentList);

        TableColumn idCol = new TableColumn("ID");
        idCol.setMinWidth(100);
        idCol.setCellValueFactory(new PropertyValueFactory<User, String>("id"));

        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(new PropertyValueFactory<User, String>("name"));

        TableColumn cardCol = new TableColumn("Card No.");
        cardCol.setMinWidth(100);
        cardCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("cardNo"));

        TableColumn groupCol = new TableColumn("Group");
        groupCol.setMinWidth(200);
        groupCol.setCellValueFactory(new PropertyValueFactory<Student, String>("group"));

        studentTable.setItems(data);
        studentTable.getColumns().addAll(idCol, nameCol, cardCol, groupCol);

    }

    public static void handleEnrollStudentButtonEvent(StudentService studentService, int idStudent, int idCourse) {
        studentService.enrollCourse(idStudent, idCourse);
    }
}
