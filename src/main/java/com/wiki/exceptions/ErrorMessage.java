package com.wiki.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Error message.
 */
@Getter
@Setter
@AllArgsConstructor

public class ErrorMessage {
 @JsonProperty("exception")
  private String message;
}
