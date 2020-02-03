package com.egstestmyquizi.demo.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaderBoardDto {
   private String name;
   private Integer points;
}
