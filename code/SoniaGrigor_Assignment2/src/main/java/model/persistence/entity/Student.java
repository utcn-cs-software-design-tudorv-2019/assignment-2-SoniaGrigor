package model.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Student extends User implements Serializable{

    @Column
    private int cardNo;

    @Column
    private String group;

    public Student() {
    }

    public Student(int cardNo, String group ) {
        this.cardNo = cardNo;
        this.group = group;
    }

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {

        return super.toString()+
                "Student{" +
                "cardNo=" + cardNo +
                ", group='" + group + '\'' +
                '}';
    }
}
