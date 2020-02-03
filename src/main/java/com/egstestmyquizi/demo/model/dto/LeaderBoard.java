package com.egstestmyquizi.demo.model.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LeaderBoard {

   private String name;
   private Integer points;


}
