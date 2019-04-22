package view;

import com.google.inject.Guice;
import com.google.inject.Injector;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.business.user.UserService;
import model.persistence.entity.User;
import model.persistence.my_utility.GuiceModule;
import model.persistence.my_utility.Utility;

import javax.inject.Inject;

import static model.persistence.my_utility.ProjectConstants.TEACHER_TITLE;

public class UserView {

    public static Injector injector = Guice.createInjector(new GuiceModule());

    @Inject
    private static UserService userService = injector.getInstance(UserService.class);

    Stage window;
    Scene sceneMain;

    int idUser = Utility.getLoggedUser();
    private User user;

    private Button basicOperationButton;
    private Button specificOperationButton;

    public UserView() {
        window = new Stage();
        window.setTitle(TEACHER_TITLE);

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
        basicOperationButton.setOnAction(e -> UserController.handleBasicOperationButtonEvent());
        leftPane.getChildren().addAll(basicOperationButton);


        VBox rightPane = new VBox(30);
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setPadding(new Insets(20, 400, 20, 20));
        specificOperationButton = new Button("Specific Operations");
        specificOperationButton.setOnAction(e -> UserController.handleSpecificOperationButtonEvent());
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
