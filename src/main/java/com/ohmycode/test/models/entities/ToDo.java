package com.ohmycode.test.models.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class ToDo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "{Max Long 200 characters}")
    @Size(min = 5, max = 200)
    private String title;
    private boolean complete = false;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private final LocalDate creation = LocalDate.now();
    @ManyToOne(cascade = {CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;

    public ToDo() {
    }

    public ToDo(String title, User user) {
        this.title = title;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public LocalDate getCreation() {
        return creation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
