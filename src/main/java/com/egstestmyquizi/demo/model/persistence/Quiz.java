package com.egstestmyquizi.demo.model.persistence;


import com.egstestmyquizi.demo.model.persistence.Answer;
import com.egstestmyquizi.demo.model.persistence.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

}
