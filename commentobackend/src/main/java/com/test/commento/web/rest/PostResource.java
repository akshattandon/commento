package com.test.commento.web.rest;

import com.test.commento.domain.Post;
import com.test.commento.domain.Post;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * REST controller for managing {@link com.test.commento.domain.Post}.
 */
@RestController
@RequestMapping("/api")
public class PostResource {

    private final String API = "https://jsonplaceholder.typicode.com/";

    private final Logger log = LoggerFactory.getLogger(PostResource.class);


    @Autowired
    private RestTemplate restTemplate;

    /**
     * {@code GET  /posts} : get all the posts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of posts in body.
     */
    @GetMapping("/posts")
    public ResponseEntity<Post[]> getAllPosts() {
        log.debug("REST request to get a page of Posts");
        return ResponseEntity.ok().body(restTemplate.getForObject(API+"posts", Post[].class));
    }

    /**
     * {@code GET  /posts/:id} : get the "id" post.
     *
     * @param id the id of the post to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the post, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        log.debug("REST request to get Post : {}", id);
        Optional<Post> post = Optional.ofNullable(restTemplate.getForObject(API+"posts/"+id, Post.class));

        return ResponseUtil.wrapOrNotFound(post);
    }


    /**
     * {@code GET  /posts/:id} : get the "id" post.
     *
     * @param id the id of the post to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the post, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/posts/{id}/comments")
    public ResponseEntity<Post> getPostWithComments(@PathVariable Long id) {
        log.debug("REST All comments for that Post : {}", id);
        Optional<Post> post = Optional.ofNullable(restTemplate.getForObject(API+"posts/"+id+"/comments", Post.class));

        return ResponseUtil.wrapOrNotFound(post);
    }


}
