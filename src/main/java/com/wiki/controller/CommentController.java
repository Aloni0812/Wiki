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

@RestController
@RequestMapping("/api/v1/Comment")
@AllArgsConstructor
public class CommentController {
  private final CommentService service;

  @GetMapping
  public List<Comment> findAllComment() {
    return service.findAllComment();
  }

  @PostMapping("saveComment")
  public Comment saveComment(@RequestBody final CommentDto comment) {
    return service.saveComment(comment);
  }

  @GetMapping("findById")
  public Comment findById(@RequestParam final Long id) {
    return service.findComment(id);
  }

  @PutMapping("updateComment")
  public Comment updateComment(@RequestBody final CommentDto commentDto) {
    return service.updateComment(commentDto);
  }

  @DeleteMapping("deleteCommment")
  public void deleteComment(@RequestParam final Long id) {
    service.deleteComment(id);
  }

}
