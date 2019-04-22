package model.persistence.repository.security;

import model.persistence.entity.Right;
import model.persistence.entity.Role;
import model.persistence.entity.User;

import java.util.List;

public class RightsRolesRepositoryPostgreSQL implements RightsRolesRepository {

    public RightsRolesRepositoryPostgreSQL() {
    }

    @Override
    public void addRole(String role) {

    }

    @Override
    public void addRight(String right) {

    }

    @Override
    public Role findRoleByTitle(String role) {
        return null;
    }

    @Override
    public Role findRoleById(int roleId) {
        return null;
    }

    @Override
    public Right findRightByTitle(String right) {
        return null;
    }

    @Override
    public void addRolesToUser(User user, List<Role> roles) {

    }

    @Override
    public List<Role> findRolesForUser(int userId) {
        return null;
    }

    @Override
    public void addRoleRight(int roleId, int rightId) {

    }
}
