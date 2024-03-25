package com.wiki.controller;

import com.wiki.dto.AppCommentDto;
import com.wiki.model.AppComment;
import com.wiki.service.AppCommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/AppComment")
@AllArgsConstructor
public class AppCommentController {
    private final AppCommentService service;

    @GetMapping
    public List<AppComment> findAllAppComment() {
        return service.findAllAppComment();
    }

    @PostMapping("saveAppComment")
    public AppComment saveAppComment(@RequestBody AppCommentDto appComment) {
        return service.saveAppComment(appComment);
    }

    @PutMapping("updateAppComment")
    public AppComment updateAppComment(@RequestBody AppComment appComment) {
        return service.updateAppComment(appComment);
    }

    @DeleteMapping("deleteAppCommment")
    public void deleteAppComment(@RequestParam Long id) {
        service.deleteAppComment(id);
    }

}
