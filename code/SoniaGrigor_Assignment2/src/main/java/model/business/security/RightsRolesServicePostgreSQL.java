package model.business.security;

import model.persistence.entity.Right;
import model.persistence.entity.Role;
import model.persistence.entity.User;
import model.persistence.my_utility.HibernateUtil;
import org.hibernate.SessionFactory;

import java.util.List;

public class RightsRolesServicePostgreSQL implements RightsRolesService {

    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public RightsRolesServicePostgreSQL() {
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
