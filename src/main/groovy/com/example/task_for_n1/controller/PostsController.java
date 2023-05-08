package com.example.task_for_n1.controller;

import com.example.task_for_n1.DTO.PostDTO;
import com.example.task_for_n1.DTO.UserPostDTO;
import com.example.task_for_n1.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostsController {
    private final PostService postService;

    public PostsController(PostService postService) {
        this.postService = postService;
    }
    @GetMapping("/top")
    public ResponseEntity<Collection<UserPostDTO>> findTopComments() {
        return ResponseEntity.ok(postService.getTopComments());
    }
    @GetMapping("/all")
    ResponseEntity<Collection<PostDTO>> findPostWithComments() {
        return ResponseEntity.ok(postService.getPostWithComments());
    }
    @GetMapping("/allpag")
    public ResponseEntity<Collection<PostDTO>> findPostsPagination(
            @RequestParam("page") Integer pageNumber,
            @RequestParam("size") Integer pageSize) {
        if (pageNumber >= 0 && pageSize > 0) {
            return ResponseEntity.ok(postService.getPostsPagination(pageNumber, pageSize));
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/body")
    ResponseEntity<Collection<PostDTO>> findPostsWithText(@RequestParam String body) {
        return ResponseEntity.ok(postService.getPostsWithText(body));
    }
}