package ru.proj.Wiki.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.proj.Wiki.model.Wiki;
import ru.proj.Wiki.repository.InMemoryWikiDAO;
import ru.proj.Wiki.servise.WikiServise;

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
    public Wiki saveWiki(Wiki Wiki) { return repository.saveWiki(Wiki); }
    @Override
    public Wiki findByRequest(String request) {
        return repository.findByRequest(request);
    }
    @Override
    public Wiki updateWiki(Wiki Wiki) {
        return updateWiki(Wiki);
    }
    @Override
    public void deleteWiki(String request) {
        repository.deleteWiki(request);

    }
}
