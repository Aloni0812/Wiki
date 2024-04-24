package com.wiki.mapper;

import com.wiki.dto.CommentDto;
import com.wiki.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class CommentMapperTest {
  @Test
  void testToDto() {
    // Arrange
    String author = "Test";
    String text = "txt";
    LocalDateTime time = LocalDateTime.now();
    Comment comment = new Comment();
    comment.setAuthor(author);
    comment.setText(text);
    comment.setTime(time);
    CommentDto commentDto = CommentMapper.toDto(comment);
    Assertions.assertNotNull(commentDto);
    Assertions.assertEquals(author, commentDto.getAuthor());
    Assertions.assertEquals(text, commentDto.getText());
    Assertions.assertEquals(time, commentDto.getTime());
  }

  @Test
  void testToDto_NullComment() {
    CommentDto commentDto = CommentMapper.toDto(null);
    Assertions.assertNull(commentDto);
  }

  @Test
  void testToEntity() {
    String author = "Test";
    String text = "Test txt";
    CommentDto commentDto = new CommentDto();
    commentDto.setAuthor(author);
    commentDto.setText(text);
    Comment comment = CommentMapper.toEntity(commentDto);
    Assertions.assertNotNull(comment);
    Assertions.assertEquals(author, comment.getAuthor());
    Assertions.assertEquals(text, comment.getText());
    Assertions.assertNotNull(comment.getTime());
  }

  @Test
  void testToEntity_NullCommentDto() {
    Comment comment = CommentMapper.toEntity(null);
    Assertions.assertNull(comment);
  }
}
