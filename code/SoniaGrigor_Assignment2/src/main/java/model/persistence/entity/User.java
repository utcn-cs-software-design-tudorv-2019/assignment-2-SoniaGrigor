package model.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_uni")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_uni")
    private int id;

    @Column
    private String name;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String CNP;

//    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER)
//    private Student student;

//    @Column(name = "group_id")
//    private String groupId;
//
//    @Column(name = "cardno")
//    private int cardNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getGroupId() {
//        return groupId;
//    }
//
//    public void setGroupId(String groupId) {
//        this.groupId = groupId;
//    }
//
//    public int getCardNo() {
//        return cardNo;
//    }
//
//    public void setCardNo(int cardNo) {
//        this.cardNo = cardNo;
//    }

//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                ", CNP='" + CNP + '\'' +
//                ", groupId='" + groupId + '\'' +
//                ", cardNo=" + cardNo +
//                '}';
//    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", CNP='" + CNP + '\'' +
                '}';
    }
}