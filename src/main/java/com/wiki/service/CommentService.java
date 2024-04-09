package com.wiki.service;

import com.wiki.cache.DataCache;
import com.wiki.dto.CommentDto;
import com.wiki.mapper.CommentMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.WikiRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@AllArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final WikiRepository wikiRepository;
    private final DataCache commentCahce;

    public List<Comment> findAllComment() {
        Object cacheObject = commentCahce.get("all");
        if(cacheObject instanceof List<?> list && list.get(0) instanceof Comment && !list.isEmpty())
            return (List<Comment>) list;
        commentCahce.put("all",commentRepository.findAll());
        return commentRepository.findAll();
    }

    public Comment findComment(Long id) {
        Object cacheObject=commentCahce.get(id.toString());
        if(cacheObject instanceof Comment commentObject)
            return commentObject;
        commentCahce.put(id.toString(),commentRepository.findCommentById(id));
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
        commentCahce.remove(id.toString());
    }


    public Comment updateComment(CommentDto commentDto) {
      commentRepository.findCommentById(commentDto.getId()).setText(commentDto.getText());
      commentCahce.remove(commentDto.getId().toString());
      commentCahce.put("all",CommentMapper.toEntity(commentDto));
      return commentRepository.findCommentById(commentDto.getId());
    }

    public Comment saveComment(CommentDto commentDto) {
        Comment comment=Objects.requireNonNull(CommentMapper.toEntity(commentDto));
        commentRepository.save(comment);
        commentCahce.put("all",comment);
        return comment;
    }
}
