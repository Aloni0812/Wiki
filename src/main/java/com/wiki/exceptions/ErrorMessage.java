package com.wiki.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ErrorMessage {
  @JsonProperty("exception")
  private String message;
  @JsonProperty("error")
  private String numberError;
  @JsonProperty("info")
  private String infoError;

}
