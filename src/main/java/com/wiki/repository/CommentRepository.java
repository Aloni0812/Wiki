package com.wiki.repository;

import com.wiki.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Comment repository.
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
  /**
   * Find comment by id comment.
   *
   * @param id the id
   * @return the comment
   */
  Comment findCommentById(Long id);
}
