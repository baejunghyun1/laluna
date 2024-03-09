package com.laluna.laluna.domain.dto.reply;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadReplyResponse {

    private Long replyno;

    private Long boardno;

    private String replytext;

    private String replyer;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private LocalDateTime regdate;

    @JsonIgnore
    private LocalDateTime moddate;


}
