package com.laluna.laluna.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyno;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "boardno")
    private Board board;

    private String replytext;

    private String replyer;

    @CreatedDate
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;

    @LastModifiedDate
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime moddate;

    public void update(String replytext,String replyer){
        this.replytext = replytext;
        this.replyer = replyer;
    }

}
