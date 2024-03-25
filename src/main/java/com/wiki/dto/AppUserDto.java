package com.wiki.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDto {
    @JsonProperty("appMail")
    private String appMail;
    @JsonProperty("name")
    private String name;
    @JsonProperty("password")
    private String password;
}
