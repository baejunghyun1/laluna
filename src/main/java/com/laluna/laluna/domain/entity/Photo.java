package com.laluna.laluna.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Photo {

    @Id
    @GeneratedValue
    private Long photono;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "memberno")
    private Member member;

    @Column(length = 200, nullable = true)
    private String link;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardno")
    private Board board;

    public void updateUrl(String link) {
        this.link = link;
    }
}
