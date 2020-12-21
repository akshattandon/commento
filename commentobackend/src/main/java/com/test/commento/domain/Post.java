package  com.test.commento.domain;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * A Post.
 */
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private String title;

    @NotNull
    private String body;

    private Comment[] comments;

    public Comment[] getComments() {
        return comments;
    }

    public void setComments(Comment[] comments) {
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public Post title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public Post body(String body) {
        this.body = body;
        return this;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Post)) {
            return false;
        }
        return id != null && id.equals(((Post) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Post{" +
                "id=" + getId() +
                ", userid=" + getUserId() +
                ", title='" + getTitle() + "'" +
                ", body='" + getBody() + "'" +
                "}";
    }
}
