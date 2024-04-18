package com.wiki.mapper;

import com.wiki.dto.CommentDto;
import com.wiki.model.Comment;
import java.time.LocalDateTime;

/**
 * The type Comment mapper.
 */
public class CommentMapper {
  private CommentMapper() {
  }

  /**
   * To dto comment dto.
   *
   * @param comment the comment
   * @return the comment dto
   */
  public static CommentDto toDto(final Comment comment) {
    if (comment != null) {
      CommentDto commentDto = new CommentDto();
      commentDto.setAuthor(comment.getAuthor());
      commentDto.setText(comment.getText());
      commentDto.setTime(comment.getTime());
      return commentDto;
    }
    return null;
  }

  /**
   * To entity comment.
   *
   * @param commentDto the comment dto
   * @return the comment
   */
  public static Comment toEntity(final CommentDto commentDto) {
    if (commentDto != null) {
      Comment comment = new Comment();
      comment.setAuthor(commentDto.getAuthor());
      comment.setText(commentDto.getText());
      comment.setTime(LocalDateTime.now());
      return comment;
    }
    return null;
  }
}
