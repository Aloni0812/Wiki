package com.wiki.service;

import com.wiki.cache.DataCache;
import com.wiki.dto.CommentDto;
import com.wiki.mapper.CommentMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class CommentService {
  private final CommentRepository commentRepository;
  private final WikiRepository wikiRepository;
  private final DataCache commentCache;

  public List<Comment> findAllComment() {
    /*Object cacheObject = commentCache.get("all");
    if (cacheObject instanceof List<?> list && list.get(0)
            instanceof Comment && !list.isEmpty()) {
      log.info("Loading data from the cache");
      return (List<Comment>) list;
    }
    commentCache.put("all", commentRepository.findAll());*/
    log.info("Loading data from databases");
    log.info("Loading Cache from databases");
    return commentRepository.findAll();
  }

  public Comment findComment(final Long id) {
    return Optional.ofNullable(commentCache.get(id.toString()))
            .filter(Comment.class::isInstance)
            .map(Comment.class::cast)
            .orElseGet(() -> {
              Comment comment = commentRepository.findCommentById(id);
              commentCache.put(id.toString(), comment);
              log.info("Found comment by id {}", id);
              return comment;
            });
  }


  public void deleteComment(final Long id) {
    Comment comment = commentRepository.findCommentById(id);
    if (comment == null) {
      return;
    }
    Wiki wiki = wikiRepository.findWikiByCommentListContains(comment);
    if (wiki != null) {
      wiki.getCommentList().remove(comment);
    }
    log.info("Deleted comment by id {}", id);
    commentRepository.deleteById(id);
    commentCache.remove(id.toString());
  }

  public Comment updateComment(final CommentDto commentDto) {
    commentRepository.findCommentById(commentDto.getId())
            .setText(commentDto.getText());
    commentRepository.findCommentById(commentDto.getId()).setAuthor(commentDto.getAuthor());
    commentCache.remove(commentDto.getId().toString());
    commentCache.put("all", CommentMapper.toEntity(commentDto));
    log.info("Updated comment with id {}", commentDto.getId());
    return commentRepository.findCommentById(commentDto.getId());
  }
  public Comment saveComment(final CommentDto commentDto) {
    Comment comment = Objects.requireNonNull(CommentMapper
            .toEntity(commentDto));
    //Comment comment = (CommentMapper.toEntity(commentDto));
    //commentCache.put("all", comment);
    log.info("Saved comment with id {}", comment.getId());
    return commentRepository.save(comment);
  }

}
