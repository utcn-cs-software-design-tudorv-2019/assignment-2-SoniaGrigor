package model.persistence.entity.builder;

import model.persistence.entity.User;

public class UserBuilder {

    private User user;

    public UserBuilder() {
        user = new User();
    }

    public UserBuilder setId(int id) {
        user.setId(id);
        return this;
    }

    public UserBuilder setName(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder setUsername(String username) {
        user.setUsername(username);
        return this;
    }

    public UserBuilder setPassword(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder setEmail(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder setCNP(String cnp) {
        user.setCNP(cnp);
        return this;
    }

//    public UserBuilder setCardNo(int cardNo) {
//        user.setCardNo(cardNo);
//        return this;
//    }
//
//    public UserBuilder setGroupId(String group) {
//        user.setGroupId(group);
//        return this;
//    }

    public User build() {
        return user;
    }

}