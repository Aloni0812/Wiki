package com.wiki.repository;

import com.wiki.model.Comment;
import com.wiki.model.Wiki;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * The interface Wiki repository.
 */
@Repository
public interface WikiRepository extends JpaRepository<Wiki, Long> {
  /**
   * Find wiki by request wiki wiki.
   *
   * @param requestWiki the request wiki
   * @return the wiki
   */
  Wiki findWikiByRequestWiki(String requestWiki);

  Wiki saveAndFlush(Wiki wiki);

  List<Wiki> findAll();

  /**
   * Find wiki by comment list contains wiki.
   *
   * @param comment the comment
   * @return the wiki
   */
  Wiki findWikiByCommentListContains(Comment comment);

  /**
   * Find wiki by request wiki and author wiki.
   *
   * @param requestWiki the request wiki
   * @param author      the author
   * @return the wiki
   */
  @Query("SELECT w FROM Wiki w JOIN w.commentList c WHERE c.author = :author "
          + "AND w.requestWiki = :requestWiki")
  Wiki findWikiByRequestWikiAndAuthor(@Param("requestWiki") String requestWiki,
                                      @Param("author") String author);
}
