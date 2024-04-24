package com.wiki.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CommentDtoTest {
  @Test
  public void testGetterAndSetter() {
    Long id = 1L;
    String author = "John";
    String text = "Hello World";
    LocalDateTime time = LocalDateTime.now();
    CommentDto commentDto = new CommentDto();
    commentDto.setId(id);
    commentDto.setAuthor(author);
    commentDto.setText(text);
    commentDto.setTime(time);
    Assertions.assertEquals(id, commentDto.getId());
    Assertions.assertEquals(author, commentDto.getAuthor());
    Assertions.assertEquals(text, commentDto.getText());
    Assertions.assertEquals(time, commentDto.getTime());
  }

  @Test
  public void testNoArgsConstructor() {
    CommentDto commentDto = new CommentDto();
    Assertions.assertNull(commentDto.getId());
    Assertions.assertNull(commentDto.getAuthor());
    Assertions.assertNull(commentDto.getText());
    Assertions.assertNotNull(commentDto.getTime());
  }

  @Test
  public void testAllArgsConstructor() {
    Long id = 1L;
    String author = "John";
    String text = "Hello World";
    LocalDateTime time = LocalDateTime.now();
    CommentDto commentDto = new CommentDto(id, author, text, time);
    Assertions.assertEquals(id, commentDto.getId());
    Assertions.assertEquals(author, commentDto.getAuthor());
    Assertions.assertEquals(text, commentDto.getText());
    Assertions.assertEquals(time, commentDto.getTime());
  }
}
