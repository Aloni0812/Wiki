package com.wiki.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    private Long id;
    private String appMail;
    private String name;
    private String password;
}
