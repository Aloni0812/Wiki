package com.wiki.service;

import com.wiki.cache.DataCache;
import com.wiki.dto.CommentDto;
import com.wiki.mapper.CommentMapper;
import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import com.wiki.repository.CommentRepository;
import com.wiki.repository.WikiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
class CommentServiceTest {
  private CommentService commentService;

  @Mock
  private CommentRepository commentRepository;

  @Mock
  private WikiRepository wikiRepository;

  @Mock
  private DataCache commentCache;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
    commentService = new CommentService(commentRepository, wikiRepository, commentCache);
  }

  @Test
  public void testFindAllComment_FromCache() {
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment());
    when(commentCache.get("all")).thenReturn(commentList);
    List<Comment> result = commentService.findAllComment();
    assertEquals(commentList, result);
  }

  @Test
  public void testFindAllComment_FromDatabase() {
    List<Comment> commentList = new ArrayList<>();
    commentList.add(new Comment());
    when(commentRepository.findAll()).thenReturn(commentList);
    when(commentCache.get("all")).thenReturn(null);
    List<Comment> result = commentService.findAllComment();
    assertEquals(commentList, result);
    verify(commentCache).put(eq("all"), any());
  }

  @Test
  public void testFindComment_FromCache() {
    Long commentId = 1L;
    Comment comment = new Comment();
    when(commentCache.get(commentId.toString())).thenReturn(comment);
    Comment result = commentService.findComment(commentId);
    assertEquals(comment, result);
  }

  @Test
  public void testFindComment_FromDatabase() {
    Long commentId = 1L;
    Comment comment = new Comment();
    when(commentRepository.findCommentById(commentId)).thenReturn(comment);
    when(commentCache.get(commentId.toString())).thenReturn(null);

    Comment result = commentService.findComment(commentId);

    assertEquals(comment, result);
    verify(commentCache).put(eq(commentId.toString()), any());
  }

  @Test
  public void testDeleteComment_CommentFound() {
    Long commentId = 1L;
    Comment comment = new Comment();
    List<Comment> commentList = new ArrayList<Comment>();
    commentList.add(comment);
    Wiki wiki = new Wiki();
    wiki.setCommentList(commentList);
    when(commentRepository.findCommentById(commentId)).thenReturn(comment);
    when(wikiRepository.findWikiByCommentListContains(comment)).thenReturn(wiki);

    commentService.deleteComment(commentId);

    verify(wikiRepository).findWikiByCommentListContains(comment);
    assertTrue(wiki.getCommentList().isEmpty());
    verify(commentRepository).deleteById(commentId);
    verify(commentCache).remove(commentId.toString());
  }

  @Test
  public void testUpdateComment() {
    Long commentId = 1L;
    String updatedText = "Updated Text";
    CommentDto commentDto = new CommentDto();
    commentDto.setId(commentId);
    commentDto.setText(updatedText);
    Comment comment = new Comment();
    when(commentRepository.findCommentById(commentId)).thenReturn(comment);
    when(commentRepository.save(comment)).thenReturn(comment);

    Comment result = commentService.updateComment(commentDto);

    assertEquals(updatedText, result.getText());
    verify(commentCache).remove(commentId.toString());
    verify(commentCache).put(eq("all"), any(Comment.class));
  }

  @Test
  public void testSaveComment() {
    CommentDto commentDto = new CommentDto();
    Comment comment = CommentMapper.toEntity(commentDto);
    when(commentRepository.save(comment)).thenReturn(comment);
    Comment result = commentService.saveComment(commentDto);
    CommentDto resultDto=CommentMapper.toDto(result);
    resultDto.setId(commentDto.getId());
    assertEquals(commentDto.getText(), resultDto.getText());
    verify(commentCache).put(eq("all"), any(Comment.class));
    verify(commentCache).put("all", result);
  }
}
