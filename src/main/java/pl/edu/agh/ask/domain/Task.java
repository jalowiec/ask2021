package pl.edu.agh.ask.domain;


import javax.persistence.*;

@Entity(name = "tasks")
public class Task {

    public Task() {
    }

    public Task(String title, String content, String userId) {
        this.id = 0L;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.done = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "done")
    private Boolean done;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getUserId() {
        return userId;
    }

    public Boolean getDone() {
        return done;
    }

    public void markDone()
    {
        done = true;
    }
}
