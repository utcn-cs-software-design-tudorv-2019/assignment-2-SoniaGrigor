package model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_course")
public class UserCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id;

    @Column(name = "user_id")
    public int userId;

    @Column(name = "course_id")
    public int courseId;

    @Column
    public int grade;

    public UserCourse() {
    }

    public UserCourse(int userId, int courseId, int grade) {
        this.userId = userId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public UserCourse(int id, int userId, int courseId, int grade) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
