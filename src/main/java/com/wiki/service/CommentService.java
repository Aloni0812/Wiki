package com.wiki.service;

import com.wiki.dto.CommentDto;
import com.wiki.mapper.CommentMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.UserRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final WikiRepository wikiRepository;

    public List<Comment> findAllComment() {
        return commentRepository.findAll();
    }

    public Comment findComment(Long id) {
        return commentRepository.findCommentById(id);
    }

    public void deleteComment(Long id){
        Comment comment = commentRepository.findCommentById(id);
        if (comment == null)
            return;
        Wiki wiki = wikiRepository.findWikiByCommentListContains(comment);
        if(wiki != null)
            wiki.getCommentList().remove(comment);
        commentRepository.deleteById(id);
    }


    public Comment updateComment(CommentDto commentDto) {
      commentRepository.findCommentById(commentDto.getId()).setText(commentDto.getText());
      return commentRepository.findCommentById(commentDto.getId());
    }

    public Comment saveComment(CommentDto commentDto) {
        return commentRepository.save(Objects.requireNonNull(CommentMapper.toEntity(commentDto)));
    }
}
