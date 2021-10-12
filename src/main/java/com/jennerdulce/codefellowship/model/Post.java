package com.jennerdulce.codefellowship.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    public String content;
    public LocalDateTime timestamp;

    @ManyToOne
    ApplicationUser myUser;


    protected Post(){

    }

    public Post(String content, ApplicationUser applicationUser, LocalDateTime timestamp){
        myUser = applicationUser;
        this.content = content;
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
