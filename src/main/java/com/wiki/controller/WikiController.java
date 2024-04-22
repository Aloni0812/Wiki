package com.wiki.controller;


import com.wiki.dto.WikiDto;
import com.wiki.model.Wiki;
import com.wiki.service.WikiService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Wiki controller.
 */
@RestController
@RequestMapping("/api/v1/Wiki")
@AllArgsConstructor
public class WikiController {
  private final WikiService service;

  /**
   * Find all wiki list.
   *
   * @return the list
   */
  @GetMapping
  public List<Wiki> findAllWiki() {
    return service.findAllWiki();
  }

  /**
   * Save wiki wiki.
   *
   * @param wikiDto the wiki dto
   * @return the wiki
   */
  @PostMapping("saveWiki")
  public Wiki saveWiki(@RequestBody final WikiDto wikiDto) {
    return service.saveWiki(wikiDto);
  }

  /**
   * Find by request wiki.
   *
   * @param request the request
   * @return the wiki
   */
  @GetMapping("findByRequest")
  public Wiki findByRequest(@RequestParam final String request) {
    return service.findByRequest(request);
  }

  /**
   * Update wiki wiki.
   *
   * @param wikidto the wikidto
   * @return the wiki
   */
  @PutMapping("updateWiki")
  public Wiki updateWiki(@RequestBody final WikiDto wikidto) {
    return service.updateWiki(wikidto);
  }

  /**
   * Delete wiki.
   *
   * @param request the request
   */
  @DeleteMapping("deleteWiki")
  public void deleteWiki(@RequestParam final String request) {
    service.deleteWiki(request);
  }

  /**
   * Find by request wiki and author wiki.
   *
   * @param requestWiki the request wiki
   * @param author      the author
   * @return the wiki
   */
  @GetMapping("findByRequestAndAuthor")
  public Wiki findByRequestWikiAndAuthor(
          @Param ("requestWiki") final String requestWiki,
          @Param("author") final String author) {
    return service.findByRequestWikiAndAuthor(requestWiki, author);
  }
}

