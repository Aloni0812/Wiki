package com.wiki.controller;


import com.wiki.dto.WikiDto;
import com.wiki.model.Wiki;
import com.wiki.service.CounterService;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RestController
@RequestMapping("/api/v1/Wiki")
@AllArgsConstructor
public class WikiController {
  private final WikiService service;
  static final Logger LOGGER = LogManager.getLogger(WikiController.class);

  @GetMapping
  public List<Wiki> findAllWiki() {
    List<Wiki> wikies = service.findAllWiki();
    return wikies;
  }

  @PostMapping("saveWiki")
  public Wiki saveWiki(@RequestBody final WikiDto wikiDto) {
    return service.saveWiki(wikiDto);
  }

  @GetMapping("findByRequest")
  public Wiki findByRequest(@RequestParam final String request) {
    return service.findByRequest(request);
  }

  @PutMapping("updateWiki")
  public Wiki updateWiki(@RequestBody final WikiDto wikidto) {
    return service.updateWiki(wikidto);
  }

  @DeleteMapping("deleteWiki")
  public void deleteWiki(@RequestParam final String request) {
    service.deleteWiki(request);
  }

  @GetMapping("findByRequestAndAuthor")
  public Wiki findByRequestWikiAndAuthor(
          @Param ("requestWiki") final String requestWiki,
          @Param("author") final String author) {
    return service.findByRequestWikiAndAuthor(requestWiki, author);
  }

  @PostMapping("/bulkSave")
  public List<Wiki> bulkSaveWiki(@RequestBody List<WikiDto> wikisList) {
    return service.bulkSaveWiki(wikisList);
  }

}

