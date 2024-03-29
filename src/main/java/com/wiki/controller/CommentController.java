package com.wiki.controller;

import com.wiki.dto.CommentDto;
import com.wiki.model.Comment;
import com.wiki.model.User;
import com.wiki.model.Wiki;
import com.wiki.service.CommentService;
import com.wiki.service.WikiService;
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

    @GetMapping("findById")
    public Comment findById(@RequestParam Long id) {
        return service.findComment(id);
    }

    @PutMapping("updateComment")
    public Comment updateComment(@RequestBody CommentDto commentDto) {
        return service.updateComment(commentDto);
    }

    @DeleteMapping("deleteCommment")
    public void deleteComment(@RequestParam Long id) {
        service.deleteComment(id);
    }

}
