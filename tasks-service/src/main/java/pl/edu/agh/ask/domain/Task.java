package pl.edu.agh.ask.domain;


import javax.persistence.*;

@Entity(name = "tasks")
public class Task {

    public Task() {
    }

    public Task(Long id, String title, String content, long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String title;

    @Column(name = "description")
    private String content;

    @Column(name = "userId")
    private long userId;

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public long getUserId() {
        return userId;
    }
}
