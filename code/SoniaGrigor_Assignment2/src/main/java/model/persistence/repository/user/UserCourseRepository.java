package model.persistence.repository.user;

import model.persistence.entity.UserCourse;

import java.util.List;

public interface UserCourseRepository {

    List<UserCourse> getAll();

    boolean update(UserCourse userCourse);

    boolean save(UserCourse userCourse);

    UserCourse get(int id);

    UserCourse getByDetails(int idUser, int idCourse);
}
