package me.whiteship.springdatademo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter @Getter
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    private int likes;

    @ManyToOne
    private Post post;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", likes=" + likes +
                '}';
    }
}
