package model.persistence.my_utility;

import com.google.inject.AbstractModule;
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
import model.persistence.repository.course.CourseRepository;
import model.persistence.repository.course.CourseRepositoryPostgreSQL;
import model.persistence.repository.security.RightsRolesRepository;
import model.persistence.repository.security.RightsRolesRepositoryPostgreSQL;
import model.persistence.repository.student.StudentRepository;
import model.persistence.repository.student.StudentRepositoryPostgreSQL;
import model.persistence.repository.user.UserRepository;
import model.persistence.repository.user.UserRepositoryPostgreSQL;

public class GuiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StudentRepository.class).to(StudentRepositoryPostgreSQL.class);
        bind(UserRepository.class).to(UserRepositoryPostgreSQL.class);
        bind(CourseRepository.class).to(CourseRepositoryPostgreSQL.class);
        bind(RightsRolesRepository.class).to(RightsRolesRepositoryPostgreSQL.class);

        bind(CourseService.class).to(CourseServicePostgreSQL.class);
        bind(RightsRolesService.class).to(RightsRolesServicePostgreSQL.class);
        bind(StudentService.class).to(StudentServicePostgreSQL.class);
        bind(AuthenticationService.class).to(AuthenticationServicePostgreSQL.class);
        bind(UserService.class).to(UserServicePostgreSQL.class);

    }

}
