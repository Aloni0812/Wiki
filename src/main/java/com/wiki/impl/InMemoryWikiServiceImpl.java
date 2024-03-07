package com.wiki.impl;

import com.wiki.model.Wiki;
import com.wiki.servise.WikiServise;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.wiki.repository.InMemoryWikiDAO;

import java.util.List;

@Service
@AllArgsConstructor
public class InMemoryWikiServiceImpl implements WikiServise {
private final InMemoryWikiDAO repository;

    @Override
    public List<Wiki> findAllWiki() {
        return repository.findAllWiki();
    }

    @Override
    public Wiki saveWiki(Wiki wiki) { return repository.saveWiki(wiki); }
    @Override
    public Wiki findByRequest(String request) {
        return repository.findByRequest(request);
    }
    @Override
    public Wiki updateWiki(Wiki wiki) {return repository.updateWiki(wiki);
    }
    @Override
    public void deleteWiki(String request) {
        repository.deleteWiki(request);

    }
}
