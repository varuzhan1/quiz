package com.egstestmyquizi.demo.model.persistence;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String question;

    @Column
    private int point;

    @Column
    private String category;

    @OneToMany(  cascade = CascadeType.ALL,
            orphanRemoval = true)
//    @JoinColumn(name = "question_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
    private   List<Answer> answers;

    public Question() {
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Question(String category, int point, String question){
        this.category = category;
        this.point = point;
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
