package com.wiki.repository;

import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {
  Wiki findWikiByRequestWiki(String requestWiki);
  Wiki saveAndFlush(Wiki wiki);
  List<Wiki> findAll();
  Wiki findWikiByCommentListContains(Comment comment);
  @Query("SELECT w FROM Wiki w JOIN w.commentList c WHERE c.author = :author " + "AND w.requestWiki = :requestWiki")
 Wiki findWikiByRequestWikiAndAuthor(@Param("requestWiki") String requestWiki,
 @Param("author") String author);
}
