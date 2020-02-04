package com.egstestmyquizi.demo.model.dto;

import com.egstestmyquizi.demo.model.persistence.Question;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtAuthenticationRequestWithQuestion {
    private JwtAuthenticationRequest jwt;
    private Question question;
}
