package com.test.commento.web.rest;

import com.test.commento.domain.Comment;
import com.test.commento.domain.Post;
import com.test.commento.domain.Post;

import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

/**
 * REST controller for managing {@link com.test.commento.domain.Post}.
 */
@RestController
@RequestMapping("/api")
public class PostResource {

    private final String API = "https://jsonplaceholder.typicode.com/";

    private final Logger log = LoggerFactory.getLogger(PostResource.class);

    private static class PostResourceException extends RuntimeException {
        private PostResourceException(String message) {
            super(message);
        }
    }


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

    /**
     * {@code POST  /posts} : Add the new comment.
     *
     * @throws RuntimeException {@code 500 (Internal Server Error)} .
     */
    @PostMapping("/posts")
    public ResponseEntity savePosts(@Valid @RequestBody Post post) {
        log.debug("REST request to add new post");
        Optional.ofNullable(post).orElseThrow(() -> new PostResource.PostResourceException("Empty object"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if( ResponseEntity.ok().body(restTemplate.postForObject(API + "/posts", post,Post.class)).getStatusCode()
            == HttpStatus.OK )
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * {@code PUT  /posts} : Update the comment.
     *
     * @throws RuntimeException {@code 500 (Internal Server Error)} .
     */
    @PutMapping("/posts/{id}")
    public ResponseEntity updateComment(@PathVariable Long id,@Valid @RequestBody Post post) {
        log.debug("REST request to update the Post {id}",id);
        Optional.ofNullable(post).orElseThrow(() -> new PostResource.PostResourceException("Empty object"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Post> entity = new HttpEntity<Post>(post, headers);
        restTemplate.put(API + "/comments/{id}" , entity , id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * {@code Delete  /posts} : Delete the post.
     *
     * @throws RuntimeException {@code 500 (Internal Server Error)} .
     */
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        log.debug("REST request to delete the Comment {}" , id);
        Optional.ofNullable(id).orElseThrow(() -> new PostResource.PostResourceException("Empty object"));
        restTemplate.delete(API + "/posts/{id}" , id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
