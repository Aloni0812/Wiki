package com.wiki.controller;

import com.wiki.dto.CommentDto;
import com.wiki.mapper.CommentMapper;
import com.wiki.model.Comment;
import com.wiki.service.CommentService;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/v1/comment")
@AllArgsConstructor
public class CommentController {
  private final CommentService service;
  private static final String COMMENTS_VIEW = "comments";
  private static final String REDIRECT = "redirect:/api/v1/wiki";
  private static final String ERROR_MESSAGE = "errorMessage";
  private static final String ERROR_REDIRECT = "comments/error";
  private static final String SUCCESS_MESSAGE = "successMessage";
  static final Logger LOGGER = LogManager.getLogger(WikiController.class);
  @GetMapping
  public String findAllComment(final Model model) {
    List<Comment> comments = service.findAllComment();
    model.addAttribute("comments", comments);
    return COMMENTS_VIEW;
  }
  @PostMapping("/saveComment")
  public String saveClient(@Valid @ModelAttribute("comment") final CommentDto commentDto,
                           final BindingResult bindingResult,
                           final RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      return "createComment";
    }
    service.saveComment(commentDto);
    redirectAttributes.addFlashAttribute("message", "Комментарий успешно сохранен");
    return "redirect:/comments";
  }

  @GetMapping("/saveComment")
  public String createComment(final @ModelAttribute("comment") CommentDto commentDto) {
    return "createComment";
  }

  @PostMapping(value = "/{id}", params = "_method=PATCH")
  public String updateComment(final @PathVariable Long id,
                           final @ModelAttribute("comment")
                           CommentDto commentDto,
                           final BindingResult bindingResult,
                           final RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().
              forEach(error -> redirectAttributes.
                      addFlashAttribute(ERROR_MESSAGE,
                              error.getDefaultMessage()));
      return "redirect:/api/v1/comment/update/" + id;
    }
    LOGGER.info("Updating a comment");
    service.updateComment(commentDto);
    LOGGER.info("Comment updated successfully");
    redirectAttributes.addFlashAttribute(
            SUCCESS_MESSAGE, "Wiki updated successfully");
    return REDIRECT;
  }
  @GetMapping("/update/{id}")
  public String showUpdateForm(final @PathVariable Long id,
                               final Model model) {
    CommentDto commentDto = CommentMapper.toDto(service.findComment(id));
    if (commentDto == null) {
      model.addAttribute(ERROR_MESSAGE,
              "Comment с ip " + id + " не найдена");
      return ERROR_REDIRECT;
    }
    model.addAttribute("comment", commentDto);
    return "updateComment";
  }

  @PostMapping(value = "/{id}", params = "_method=DELETE")
  public String deleteCommentById(
          @PathVariable final Long id, final Model model) {
    LOGGER.info("Deleting comment by id: {}", id);
    try {
      service.deleteComment(id);
      LOGGER.info("Comment deleted successfully: id {}", id);
      return REDIRECT;
    } catch (EntityNotFoundException e) {
      LOGGER.error("Comment not found: {}",id);
      model.addAttribute(ERROR_MESSAGE, e.getMessage());
      return ERROR_REDIRECT;
    }
  }

  @GetMapping("/delete/{id}")
  public String showDeleteForm(final @PathVariable Long id,
                               final Model model) {
    try {
      final Comment comment = service.findComment(id);
      model.addAttribute(COMMENTS_VIEW, comment);
      return "deleteComment";
    } catch (Exception e) {
      model.addAttribute(ERROR_MESSAGE, e.getMessage());
      return ERROR_REDIRECT;
    }
  }
}
