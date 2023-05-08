package com.example.task_for_n1.repository;

import com.example.task_for_n1.model.Post;
import com.example.task_for_n1.projection.PostProjectoin;
import com.example.task_for_n1.projection.UserProjectoin;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>, JpaSpecificationExecutor<Post> {

    @Query(value = "SELECT u.id AS userId, u.username AS username, " +
            "COUNT(DISTINCT p.id) AS allPostCount, " +
            "COUNT(DISTINCT c.id) AS allCommentsCount, " +
            "(SELECT p2.id FROM post AS p2 WHERE p2.user_id = u.id " +
            "ORDER BY (SELECT COUNT(*) FROM comment WHERE post_id = p2.id) " +
            "DESC, p2.id DESC LIMIT 1) AS lastPostId " +
            "FROM users AS u " +
            "JOIN post AS p ON u.id = p.user_id " +
            "LEFT JOIN comment AS c ON u.id = c.user_id " +
            "GROUP BY u.id, u.username " +
            "ORDER BY allCommentsCount DESC " +
            "LIMIT 10", nativeQuery = true)
    List<UserProjectoin> getTopComments();
    @EntityGraph(attributePaths = {"comments", "user"})
    List<Post> findAllBy();
    List<PostProjectoin> findAllBy(Pageable pageable);
}

