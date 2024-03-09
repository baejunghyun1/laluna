package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class Board extends Date{

    @Id
    @GeneratedValue
    private Long boardno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "memberno")
    private Member member;

//    @Column(length = 50, nullable = false)
//    private String writer;

    @Column(length = 50)
    private String title;

    @Column(length = 1000)
    private String content;

    @Column(length = 50)
    private String category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Photo> photos;

    public void update(List<Photo> photos, String title, String content, String category) {
        System.out.println("Received values: " + photos + ", " + title + ", " + content + ", " + category);
        System.out.println("Before update: " + this.photos + ", " + this.title + ", " + this.content + ", " + this.category);

        this.photos = photos;
        this.title = title;
        this.content = content;
        this.category = category;

        System.out.println("After update: " + this.photos + ", " + this.title + ", " + this.content + ", " + this.category);
    }

}
