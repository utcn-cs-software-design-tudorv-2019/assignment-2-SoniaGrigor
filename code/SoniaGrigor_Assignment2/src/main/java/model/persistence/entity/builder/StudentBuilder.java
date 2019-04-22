package model.persistence.entity.builder;

import model.persistence.entity.Student;

public class StudentBuilder {

    private Student student;

    public StudentBuilder() {
        student = new Student();
    }

    public StudentBuilder setId(int id) {
        student.setId(id);
        return this;
    }

    public StudentBuilder setName(String name) {
        student.setName(name);
        return this;
    }

    public StudentBuilder setUsername(String studentname) {
        student.setUsername(studentname);
        return this;
    }

    public StudentBuilder setPassword(String password) {
        student.setPassword(password);
        return this;
    }

    public StudentBuilder setEmail(String email) {
        student.setEmail(email);
        return this;
    }

    public StudentBuilder setCNP(String cnp) {
        student.setCNP(cnp);
        return this;
    }

    public StudentBuilder setCardNo(int cardNo) {
        student.setCardNo(cardNo);
        return this;
    }

    public StudentBuilder setGroup(int group) {
        student.setGroup(group);
        return this;
    }

    public Student build() {
        return student;
    }
}
