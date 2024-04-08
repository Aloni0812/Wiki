package com.wiki.repository;

import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {
    Wiki findWikiByRequestWiki(String requestWiki);
    Wiki saveAndFlush(Wiki wiki);
    List<Wiki> findAll();
    Wiki findWikiByCommentListContains(Comment comment);

    @Query("SELECT d FROM Wiki d INNER JOIN d.commentList c WHERE c.author = :author "+"AND d.requestWiki = :reqestWiki")
    Wiki findByRequestWikiAndAuthor(@Param("requestWiki") String requestWiki, @Param("author") String author);
}
