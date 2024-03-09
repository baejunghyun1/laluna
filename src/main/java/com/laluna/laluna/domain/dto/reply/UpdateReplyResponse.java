package com.laluna.laluna.domain.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReplyResponse {

    private Long replyno;

    private Long boardno;

    private String replytext;

    private String replyer;

    private LocalDateTime regfate;

    private LocalDateTime modfate;
}
