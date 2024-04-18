package com.wiki.controller;

import com.wiki.dto.CommentDto;
import com.wiki.model.Comment;
import com.wiki.service.CommentService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Comment controller.
 */
@RestController
@RequestMapping("/api/v1/Comment")
@AllArgsConstructor
public class CommentController {
  private final CommentService service;

  /**
   * Find all comment list.
   *
   * @return the list
   */
  @GetMapping
  public List<Comment> findAllComment() {
    return service.findAllComment();
  }

  /**
   * Save comment comment.
   *
   * @param comment the comment
   * @return the comment
   */
  @PostMapping("saveComment")
  public Comment saveComment(@RequestBody CommentDto comment) {
    return service.saveComment(comment);
  }

  /**
   * Find by id comment.
   *
   * @param id the id
   * @return the comment
   */
  @GetMapping("findById")
  public Comment findById(@RequestParam Long id) {
    return service.findComment(id);
  }

  /**
   * Update comment comment.
   *
   * @param commentDto the comment dto
   * @return the comment
   */
  @PutMapping("updateComment")
  public Comment updateComment(@RequestBody CommentDto commentDto) {
    return service.updateComment(commentDto);
  }

  /**
   * Delete comment.
   *
   * @param id the id
   */
  @DeleteMapping("deleteCommment")
  public void deleteComment(@RequestParam Long id) {
    service.deleteComment(id);
  }

}
