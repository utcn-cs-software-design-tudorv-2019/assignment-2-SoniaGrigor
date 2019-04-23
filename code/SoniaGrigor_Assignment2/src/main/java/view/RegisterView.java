package view;

import controller.RegisterController;
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

import java.io.FileNotFoundException;

import static model.persistence.my_utility.ProjectConstants.REGISTER_TITLE;

public class RegisterView {

    Stage window;

    private TextField nameField;
    private TextField usernameField;
    private PasswordField passwordField;
    private TextField emailField;
    private TextField cnpField;
    private Button registerButton;

    public RegisterView() throws FileNotFoundException {

        window = new Stage();
        window.setTitle(REGISTER_TITLE);

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
        nameField = new TextField();
        nameField.setMinWidth(300);
        usernameField = new TextField();
        usernameField.setMinWidth(300);
        passwordField = new PasswordField();
        passwordField.setMinWidth(300);
        emailField = new TextField();
        emailField.setMinWidth(300);
        cnpField = new TextField();
        cnpField.setMinWidth(300);

        rightPane.getChildren().addAll(nameField, usernameField, passwordField, emailField, cnpField);

        VBox bottomPane = new VBox(30);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setPadding(new Insets(20, 20, 100, 20));
        registerButton = new Button("Register");
        String role = "BASIC";
        registerButton.setOnAction(e -> RegisterController.handleRegisterButtonEvent(nameField.getText(), usernameField.getText(), passwordField.getText(), emailField.getText(), cnpField.getText(), role));
        bottomPane.getChildren().addAll(registerButton);

        layout.setTop(topPane);
        layout.setLeft(leftPane);
        layout.setRight(rightPane);
        layout.setBottom(bottomPane);
        Scene scene = new Scene(layout, 1200, 800);
        window.setScene(scene);
        window.show();
    }

}
