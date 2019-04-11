package model.persistence.my_utility;

import model.business.course.CourseService;
import model.business.course.CourseServicePostgreSQL;
import model.business.security.RightsRolesService;
import model.business.security.RightsRolesServicePostgreSQL;
import model.business.student.StudentService;
import model.business.student.StudentServicePostgreSQL;
import model.business.user.AuthenticationService;
import model.business.user.AuthenticationServicePostgreSQL;
import model.business.user.UserService;
import model.business.user.UserServicePostgreSQL;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.course.CourseRepositoryPostgreSQL;
import model.persistence.repository.security.RightsRolesRepository;
import model.persistence.repository.security.RightsRolesRepositoryPostgreSQL;
import model.persistence.repository.student.StudentRepository;
import model.persistence.repository.student.StudentRepositoryPostgreSQL;
import model.persistence.repository.user.UserRepository;
import model.persistence.repository.user.UserRepositoryPostgreSQL;


public class HibernateUtil {
    private static final SessionFactory ourSessionFactory;

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final RightsRolesService rightsRolesService;
    private final StudentService studentService;
    private final CourseService courseService;

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;

    public HibernateUtil(Boolean componentsForTests) {
        SessionFactory connection = HibernateUtil.getSessionFactory();

        rightsRolesRepository = new RightsRolesRepositoryPostgreSQL(connection);
        courseRepository=new CourseRepositoryPostgreSQL(connection);
        studentRepository = new StudentRepositoryPostgreSQL(connection);
        userRepository= new UserRepositoryPostgreSQL(connection);

        rightsRolesService = new RightsRolesServicePostgreSQL(connection);
        userService = new UserServicePostgreSQL(connection);
        courseService= new CourseServicePostgreSQL(connection, courseRepository);
        studentService= new StudentServicePostgreSQL(connection);
        authenticationService = new AuthenticationServicePostgreSQL();
    }

    static {
        try {

            Configuration configuration = new Configuration();

            configuration.configure();

            StandardServiceRegistryBuilder builder= new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties());

            ourSessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Throwable ex) {
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return ourSessionFactory;
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserService getUserService() {
        return userService;
    }

    public RightsRolesService getRightsRolesService() {
        return rightsRolesService;
    }

    public StudentService getStudentService() {
        return studentService;
    }

    public CourseService getCourseService() {
        return courseService;
    }

    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }
}
