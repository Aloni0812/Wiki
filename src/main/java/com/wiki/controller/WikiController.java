package com.wiki.controller;


import com.wiki.model.Wiki;
import com.wiki.servise.WikiServise;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Wiki")
@AllArgsConstructor
public class WikiController {
    private final WikiServise service;

    @GetMapping
    public List<Wiki> findAllWIKI() {
        return service.findAllWiki();
    }

    @PostMapping("saveWiki")
    public String saveWiki(@RequestBody Wiki wiki) {
        service.saveWiki(wiki);
        return "Wiki successfully saved";
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

