package com.egstestmyquizi.demo.model.persistence;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Table(name = "answer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    String answer;

//    public boolean equals(Answer answer){
//        if(this.answer.equals(answer)){
//            return true;
//        } else {
//            return false;
//        }
//    }

}
