package controller;

import model.business.course.CourseService;
import model.business.student.StudentService;
import model.business.user.AuthenticationService;
import model.business.user.UserService;
import view.SpecificOperationView;
import view.StudentView;

public class UserController {
    public static void handleBasicOperationButtonEvent(AuthenticationService authenticationService, CourseService courseService, StudentService studentService, UserService userService) {
        new StudentView(authenticationService, courseService, studentService, userService);
    }

    public static void handleSpecificOperationButtonEvent(AuthenticationService authenticationService, CourseService courseService, StudentService studentService, UserService userService) {
        new SpecificOperationView(authenticationService, courseService, studentService, userService);
    }

}
