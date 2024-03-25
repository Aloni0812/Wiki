package com.wiki.repository;

import com.wiki.model.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {
    void deleteByRequestWiki(String requestWiki);
    Wiki findWikiByRequestWiki(String requestWiki);
    Wiki saveAndFlush(Wiki wiki);
}
