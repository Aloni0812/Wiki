package com.wiki.repository;

import com.wiki.model.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {
    Wiki findWikiByRequestWiki(String requestWiki);

    Wiki saveAndFlush(Wiki wiki);

    List<Wiki> findAll();
}
