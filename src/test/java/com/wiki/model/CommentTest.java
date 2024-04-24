package com.wiki.model;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CommentTest {
  @Test
  public void testCommentConstructor() {
    Long id = 1L;
    String author = "Sasha";
    String text = "Test";
    LocalDateTime time = LocalDateTime.now();
    Comment comment = new Comment(id, author, text, time);
    assertNotNull(comment);
    assertEquals(id, comment.getId());
    assertEquals(author, comment.getAuthor());
    assertEquals(text, comment.getText());
    assertEquals(time, comment.getTime());
  }

  @Test
  public void testCommentGettersAndSetters() {
    Comment comment = new Comment();
    Long id = 1L;
    String author = "Sasha";
    String text = "Test";
    LocalDateTime time = LocalDateTime.now();
    comment.setId(id);
    comment.setAuthor(author);
    comment.setText(text);
    comment.setTime(time);
    assertEquals(id, comment.getId());
    assertEquals(author, comment.getAuthor());
    assertEquals(text, comment.getText());
    assertEquals(time, comment.getTime());
  }
}
