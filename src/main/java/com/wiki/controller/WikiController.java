package com.wiki.controller;

import com.wiki.dto.CommentDto;
import com.wiki.dto.WikiDto;
import com.wiki.mapper.WikiMapper;
import com.wiki.model.Wiki;
import com.wiki.service.CommentService;
import com.wiki.service.WikiService;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/v1/wiki")
@AllArgsConstructor
public class WikiController {
  private final WikiService service;
  private final CommentService commentService;
  static final Logger LOGGER = LogManager.getLogger(WikiController.class);
  private static final String REDIRECT = "redirect:/api/v1/wiki";
  private static final String WIKIES_VIEW = "wikies";
  private static final String ERROR_MESSAGE = "errorMessage";
  private static final String ERROR_REDIRECT = "comments/error";
  private static final String SUCCESS_MESSAGE = "successMessage";

  @GetMapping
  public String findAllWiki(final Model model){
    LOGGER.info("find all Wiki");
    List<Wiki> wikies = service.findAllWiki();
    model.addAttribute("wikies", wikies);
    return WIKIES_VIEW;
  }

  @PostMapping("/saveWiki")
  public String saveWiki(@Valid @ModelAttribute("wiki") final WikiDto wikiDto,
                       final BindingResult bindingResult,
                       final RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().
              forEach(error -> redirectAttributes.
                      addFlashAttribute(ERROR_MESSAGE,
                              error.getDefaultMessage()));
      service.saveWiki(wikiDto);
      return "createWiki";
    }
    try {
      LOGGER.info("Saving a wiki");
      service.saveWiki(wikiDto);
      LOGGER.info("Wiki saved successfully");
      redirectAttributes.
              addFlashAttribute(SUCCESS_MESSAGE,
                      "Wiki saved successfully");
      return REDIRECT;
    } catch (RuntimeException e) {
      LOGGER.error("Error saving wiki", e);
      redirectAttributes.
              addFlashAttribute(ERROR_MESSAGE,
                      "Error saving wiki: " + e.getMessage());
      return ERROR_REDIRECT;
    }
  }

  @GetMapping("/saveWiki")
  public String createWiki(final @ModelAttribute("wiki")
                                WikiDto wikiDto) {
    return "createWiki";
  }

  @GetMapping("findByRequest")
  public Wiki findByRequest(@RequestParam final String request) {
    return service.findByRequest(request);
  }

  @PostMapping(value = "/{id}", params = "_method=PATCH")
  public String updateWiki(final @PathVariable Long id,
                              final @ModelAttribute("wiki")
                              WikiDto wikidto,
                              final BindingResult bindingResult,
                              final RedirectAttributes redirectAttributes) {
    if (bindingResult.hasErrors()) {
      bindingResult.getAllErrors().
              forEach(error -> redirectAttributes.
                      addFlashAttribute(ERROR_MESSAGE,
                              error.getDefaultMessage()));
      return "redirect:/api/v1/wiki/update/" + id;
    }
      LOGGER.info("Updating a wiki");
      service.updateWiki(wikidto);
      LOGGER.info("Wiki updated successfully");
      redirectAttributes.addFlashAttribute(
              SUCCESS_MESSAGE, "Wiki updated successfully");
      return REDIRECT;
  }
 @GetMapping("/update/{id}")
  public String showUpdateForm(final @PathVariable Long id,
                               final Model model) {
    WikiDto wikidto = WikiMapper.toDto(service.findWikiId(id));
    if (wikidto == null) {
      model.addAttribute(ERROR_MESSAGE,
              "Wiki с ip " + id + " не найдена");
      return ERROR_REDIRECT;
    }
    model.addAttribute("wiki", wikidto);
    return "updateWiki";
  }

  @PostMapping(value = "/{delRequest}", params = "_method=DELETE")
  public String deleteWikiById(
          @PathVariable final String delRequest, final Model model) {
    LOGGER.info("Deleting wiki by id: {}", delRequest);
    try {
      service.deleteWiki(delRequest);
      LOGGER.info("Wiki deleted successfully: {}", delRequest);
      return REDIRECT;
    } catch (EntityNotFoundException e) {
      LOGGER.error("Wiki not found: {}", delRequest);
      model.addAttribute(ERROR_MESSAGE, e.getMessage());
      return ERROR_REDIRECT;
    }
  }

  @GetMapping("/delete/{delRequest}")
  public String showDeleteForm(final @PathVariable String delRequest,
                               final Model model) {
    try {
      final Wiki wiki = service.findByRequest(delRequest);
      model.addAttribute(WIKIES_VIEW, wiki);
      return "deleteWiki";
    } catch (Exception e) {
      model.addAttribute(ERROR_MESSAGE, e.getMessage());
      return ERROR_REDIRECT;
    }
  }
  @PostMapping(value = "/saveComment/{id}", params = "_method=POST")
  public String commentWikiById(
          @Valid@ModelAttribute("commentDto") final CommentDto commentDto,
          @PathVariable final Long id, final Model model) {
    try {
      WikiDto wikiDto= WikiMapper.toDto(service.findWikiId(id));
      List<CommentDto> commentDtoList = wikiDto.getCommentDtoList();
      System.out.println(commentDtoList.size());
      commentDtoList.add(commentDto);
      wikiDto.setCommentDtoList(commentDtoList);
      service.updateWiki(wikiDto);
      commentService.saveComment(commentDto);
      LOGGER.info("Wiki update comment. Id wiki: {}", id);
      Wiki wiki= service.findWikiId(id);
      model.addAttribute("wiki", wiki);
      model.addAttribute("comments", wiki.getCommentList());
      return "comments";
    } catch (EntityNotFoundException e) {
      LOGGER.error("Wiki not found: {}", id);
      model.addAttribute(ERROR_MESSAGE, e.getMessage());
      return ERROR_REDIRECT;
    }
  }

  @GetMapping("/comment/{id}")
  public String showCommentForm(final @PathVariable Long id,
                               final Model model) {
    try {
      final Wiki wiki = service.findWikiId(id);
      model.addAttribute("wiki", wiki);
      model.addAttribute("comments", wiki.getCommentList());
      return "comments";
    } catch (Exception e) {
      model.addAttribute(ERROR_MESSAGE, e.getMessage());
      return ERROR_REDIRECT;
    }
  }
  @GetMapping("/saveComment/{id}")
  public String createComment(final @ModelAttribute("comment") CommentDto commentDto,
                              Model model,@PathVariable Long id) {
    Wiki wiki = service.findWikiId(id);
    model.addAttribute("wiki", wiki);
    model.addAttribute("commentDto", commentDto);
    return "createComment";
  }
}

