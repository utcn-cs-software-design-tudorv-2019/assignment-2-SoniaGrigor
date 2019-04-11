package model.persistence.repository.security;

import model.persistence.entity.Right;
import model.persistence.entity.Role;
import model.persistence.entity.User;

import java.util.List;

public interface RightsRolesRepository {

    void addRole(String role);

    void addRight(String right);

    Role findRoleByTitle(String role);

    Role findRoleById(int roleId);

    Right findRightByTitle(String right);

    void addRolesToUser(User user, List<Role> roles);

    List<Role> findRolesForUser(int userId);

    void addRoleRight(int roleId, int rightId);
}