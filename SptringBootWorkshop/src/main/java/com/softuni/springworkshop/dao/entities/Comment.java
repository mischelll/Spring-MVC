package com.softuni.springworkshop.dao.entities;

import org.springframework.context.annotation.EnableLoadTimeWeaving;

import javax.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {
    private Integer score;
    private String textContent;
    private User author;
    private Homework homework;

    public Comment() {
    }

    @Column(name = "score", nullable = false)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Lob
    @Column(name = "text_content", nullable = false)
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @ManyToOne
    @JoinColumn(name = "homework_id", referencedColumnName = "id")
    public Homework getHomework() {
        return homework;
    }

    public void setHomework(Homework homework) {
        this.homework = homework;
    }
}
