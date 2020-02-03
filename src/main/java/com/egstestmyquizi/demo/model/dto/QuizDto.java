package com.egstestmyquizi.demo.model.dto;

import com.egstestmyquizi.demo.model.persistence.Question;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class QuizDto {
    private Integer id;
    private  String name;
    private List<QuestionDto> questions;
}
