package com.egstestmyquizi.demo.model.dto;

import com.egstestmyquizi.demo.model.persistence.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {


    private int id;
    private String name;
    private String surname;
    private Role role;
    private String email;

}
