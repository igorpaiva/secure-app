package com.springsecured.dto;

import lombok.Data;

@Data
//@AllArgsConstructor
public class CurrentUserDTO extends ModelDTO{
    private String username;
    private String password;
}
