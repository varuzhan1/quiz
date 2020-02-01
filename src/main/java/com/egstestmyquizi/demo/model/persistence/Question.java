package com.egstestmyquizi.demo.model.persistence;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String question;

    @Column
    private int point;

    @OneToMany(mappedBy = "question")
//    @JsonIgnore
    private   List<Answer> answers;

    @OneToOne
    private Answer correctAnswer;

    @ManyToOne
    private Quiz quiz;
}
