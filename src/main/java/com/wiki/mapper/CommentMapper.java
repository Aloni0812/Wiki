package com.wiki.mapper;

import com.wiki.dto.CommentDto;
import com.wiki.model.Comment;

import java.time.LocalDateTime;

public class CommentMapper {
    private CommentMapper() {
    }

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
