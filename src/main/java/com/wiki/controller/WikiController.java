package com.wiki.controller;


import com.wiki.dto.WikiDto;
import com.wiki.model.Wiki;
import com.wiki.service.WikiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Wiki")
@AllArgsConstructor
public class WikiController {
    private final WikiService service;

    @GetMapping
    public List<Wiki> findAllWiki() {
        return service.findAllWiki();
    }

    @PostMapping("saveWiki")
    public Wiki saveWiki(@RequestBody WikiDto wikiDto) {;
        return service.saveWiki(wikiDto);
    }

    @GetMapping("findByRequest")
    public Wiki findByRequest(@RequestParam String request) {
        return service.findByRequest(request);
    }

    @PutMapping("updateWiki")
    public Wiki updateWiki(@RequestBody Wiki wiki) {
        return service.updateWiki(wiki);
    }

    @DeleteMapping("deleteWiki")
    public void deleteWiki(@RequestParam String request) {
        service.deleteWiki(request);
    }
}

