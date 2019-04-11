import javafx.application.Application;
import javafx.stage.Stage;
import model.persistence.my_utility.HibernateUtil;
import view.LoginView;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HibernateUtil componentFactory = ComponentFactory.getInstance(false);
        //SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        //new LoginView(sessionFactory.getAuthenticationService(), sessionFactory.getCourseService(), sessionFactory.getStudentService(), sessionFactory.getUserService());
        new LoginView(componentFactory.getAuthenticationService(), componentFactory.getCourseService(), componentFactory.getStudentService(), componentFactory.getUserService());
    }
}
