package com.laluna.laluna.domain.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReplyRequest {

    private String replytext;

    private String replyer;

    private Long boardno;
}
