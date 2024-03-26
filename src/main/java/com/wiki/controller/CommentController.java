package com.wiki.controller;

import com.wiki.dto.CommentDto;
import com.wiki.model.Comment;
import com.wiki.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Comment saveComment(@RequestBody CommentDto comment) {
        return service.saveComment(comment);
    }

    @PutMapping("updateComment")
    public Comment updateComment(@RequestBody CommentDto commentDto) {
        return service.updateComment(commentDto);
    }

    @DeleteMapping("deleteAppCommment")
    public void deleteComment(@RequestParam Long id) {
        service.deleteComment(id);
    }

}
