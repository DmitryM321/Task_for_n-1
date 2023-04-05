package com.example.task_for_n1.service;

import com.example.task_for_n1.DTO.CommentDTO;
import com.example.task_for_n1.DTO.PostDTO;
import com.example.task_for_n1.DTO.UserPostDTO;
import com.example.task_for_n1.projection.PostProjectoin;
import com.example.task_for_n1.projection.UserProjectoin;
import com.example.task_for_n1.projection.CommentProjectoin;
import com.example.task_for_n1.repository.CommentRepository;
import com.example.task_for_n1.repository.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }
    public List<UserPostDTO> getTopComments() {
        List<UserProjectoin> users = postRepository.getTopComments();
        return users.stream().map(UserProjectoin::fromUserProjectionToUserDTO)
                .collect(Collectors.toList());
    }
    public List<PostDTO> getPostWithComments() {
        return postRepository.findAllBy().stream().map(PostDTO::fromPostToPostDTO).collect(Collectors.toList());
    }
    public List<PostDTO> getPostsPagination(Integer pageNumber, Integer pageSize) {
        List<PostProjectoin> postProjection = postRepository.findAllBy(PageRequest.of(pageNumber - 1, pageSize));
        List<CommentProjectoin> commentProjection = commentRepository.getPostIdIn(postProjection.stream()
                .map(PostProjectoin::getId)
                .collect(Collectors.toList()));
        return postProjection.stream()
                .map(PostProjectoin::fromPostProjectionToPostDTO)
                .peek(postDTO -> postDTO.setComments((commentProjection.stream()
                        .filter(comment -> comment.getPostId().equals(postDTO.getId())))
                        .map(CommentProjectoin::fromCommentProjectionToCommentDTO).collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
    public List<PostDTO> getPostsWithText(String body) {
        return postRepository.findAll((root, query, criteriaBuilder) -> {
                    root.fetch("comments", JoinType.LEFT).fetch("user", JoinType.LEFT);
                    query.distinct(true);
                    return criteriaBuilder.like(root.join("comments").get("body"), "%" + body + "%");})
                .stream()
                .map(PostDTO::fromPostToPostDTO)
                .collect(Collectors.toList());
    }
}