package com.test.commento.web.rest;

import com.test.commento.domain.Comment;
import com.test.commento.domain.Post;
import com.test.commento.domain.User;
import com.test.commento.security.SecurityUtils;
import com.test.commento.service.dto.UserDTO;
import com.test.commento.web.rest.errors.EmailAlreadyUsedException;
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
 * REST controller for managing {@link com.test.commento.domain.Comment}.
 */
@RestController
@RequestMapping("/api")
public class CommentResource {

    private final String API = "https://jsonplaceholder.typicode.com/";

    private final Logger log = LoggerFactory.getLogger(CommentResource.class);

    private static class CommentResourceException extends RuntimeException {
        private CommentResourceException(String message) {
            super(message);
        }
    }

    @Autowired
    private RestTemplate restTemplate;

    /**
     * {@code GET  /comments} : get all the comments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comments in body.
     */
    @GetMapping("/comment")
    public ResponseEntity<Comment[]> getAllComments(@RequestParam Optional<String> postId) {
        log.debug("REST request to get all comments {}", postId.isPresent());
       if(postId.isPresent())
            return ResponseEntity.ok().body(restTemplate.getForObject(API + "/comments?postId=" + postId.get(), Comment[].class));
        else
            return ResponseEntity.ok().body(restTemplate.getForObject(API + "/comments", Comment[].class));

    }

    /**
     * {@code POST  /comment} : Add the new comment.
     *
     * @throws RuntimeException {@code 500 (Internal Server Error)} .
     */
    @PostMapping("/comment")
    public ResponseEntity saveComment(@Valid @RequestBody Comment comment) {
        log.debug("REST request to add new Comment");
        Optional.ofNullable(comment).orElseThrow(() -> new CommentResourceException("Empty object"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if( ResponseEntity.ok().body(restTemplate.postForObject(API + "/comments?postId=" + comment.getPostId(), comment,Comment.class)).getStatusCode()
        == HttpStatus.OK)
        return new ResponseEntity<>(HttpStatus.OK);
        else
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * {@code PUT  /comment} : Update the comment.
     *
     * @throws RuntimeException {@code 500 (Internal Server Error)} .
     */
    @PutMapping("/comment/{id}")
    public ResponseEntity updateComment(@PathVariable Long id,@Valid @RequestBody Comment comment) {
        log.debug("REST request to update the Comment");
        Optional.ofNullable(comment).orElseThrow(() -> new CommentResourceException("Empty object"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Comment> entity = new HttpEntity<Comment>(comment, headers);
        restTemplate.put(API + "/comments/{id}" , entity , comment.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * {@code Delete  /comment} : Delete the comment.
     *
     * @throws RuntimeException {@code 500 (Internal Server Error)} .
     */
    @DeleteMapping("/comment/{id}")
    public ResponseEntity deleteComment(@PathVariable Long id) {
        log.debug("REST request to delete the Comment {id}",id);
        Optional.ofNullable(id).orElseThrow(() -> new CommentResourceException("Empty object"));
        restTemplate.delete(API + "/comments/{id}" ,id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
