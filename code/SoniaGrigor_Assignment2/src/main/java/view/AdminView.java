package view;

import com.google.inject.Guice;
import com.google.inject.Injector;
import controller.AdminController;
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
import model.business.user.UserService;
import model.persistence.entity.User;
import model.persistence.my_utility.GuiceModule;
import model.persistence.my_utility.UtilityAuthorization;

import javax.inject.Inject;

import static model.persistence.my_utility.ProjectConstants.STUDENT_TITLE;

public class AdminView {
    private static User user;
    Stage window;
    Scene sceneMain;
    int idUser = UtilityAuthorization.getLoggedUser();
    private Injector injector = Guice.createInjector(new GuiceModule());
    @Inject
    private UserService userService = injector.getInstance(UserService.class);
    private TextField nameField;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField emailField;
    private TextField cnpField;

    private Button updateButton;
    private Button deleteButton;

    public AdminView() {
        window = new Stage();
        window.setTitle(STUDENT_TITLE);

        user = userService.findById(idUser);

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
        leftPane.getChildren().addAll(labelName, labelUsername, labelPassword, labelEmail, labelCNP);

        VBox rightPane = new VBox(30);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(20, 400, 20, 20));
        nameField = new TextField(user.getName());
        nameField.setMinWidth(300);
        usernameField = new TextField(user.getUsername());
        usernameField.setMinWidth(300);
        passwordField = new PasswordField();
        passwordField.setMinWidth(300);
        emailField = new TextField(user.getEmail());
        emailField.setMinWidth(300);
        cnpField = new TextField(user.getCNP());
        cnpField.setMinWidth(300);

        rightPane.getChildren().addAll(nameField, usernameField, passwordField, emailField, cnpField);

        VBox bottomPane = new VBox(30);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(20, 20, 100, 20));
        updateButton = new Button("Update Information");

        updateButton.setOnAction(e -> AdminController.handleUpdateButtonEvent(userService, nameField.getText(), usernameField.getText(), passwordField.getText(), emailField.getText(), cnpField.getText()));
        deleteButton = new Button("Delete Accout");
        deleteButton.setOnAction(e -> AdminController.handleDeleteButtonEvent());
        bottomPane.getChildren().addAll(updateButton, deleteButton);

        layout.setTop(topPane);
        layout.setLeft(leftPane);
        layout.setRight(rightPane);
        layout.setBottom(bottomPane);

        sceneMain = new Scene(layout, 1200, 800);
        window.setScene(sceneMain);
        window.show();
    }
}
