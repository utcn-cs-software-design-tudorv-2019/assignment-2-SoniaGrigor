package model.business.user;

import model.persistence.entity.User;

public interface UserService {

    boolean update(User user);

    boolean save(User user);

    boolean removeById(int id);

    User findById(int idUser);

    boolean generateRaport(int userId);
}
