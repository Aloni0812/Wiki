package com.wiki.exceptions;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class ErrorMessageTest {

  @Test
  void testErrorMessageConstructor() {
    String message = "Exception";
    String numberError = "404";
    String infoError = "Bad req";
    ErrorMessage errorMessage = new ErrorMessage(message, numberError, infoError);
    assertEquals(message, errorMessage.getMessage());
    assertEquals(numberError, errorMessage.getNumberError());
    assertEquals(infoError, errorMessage.getInfoError());
  }

  @Test
  void testErrorMessageJsonSerialization() throws Exception {
    String message = "Exception";
    String numberError = "404";
    String infoError = "Bad req";
    ErrorMessage errorMessage = new ErrorMessage(message, numberError, infoError);
    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(errorMessage);
    String expectedJson = "{\"exception\":\"Exception\",\"error\":\"404\",\"info\":\"Bad req\"}";
    assertEquals(expectedJson, json);
  }

  @Test
  void testErrorMessageJsonDeserialization() throws Exception {
    String json = "{\"exception\":\"Exception\",\"error\":\"404\",\"info\":\"Bad req\"}";
    ObjectMapper objectMapper = new ObjectMapper();
    ErrorMessage errorMessage = objectMapper.readValue(json, ErrorMessage.class);
    String message = "Exception";
    String numberError = "404";
    String infoError = "Bad req";
    assertEquals(message, errorMessage.getMessage());
    assertEquals(numberError, errorMessage.getNumberError());
    assertEquals(infoError, errorMessage.getInfoError());
  }
}
