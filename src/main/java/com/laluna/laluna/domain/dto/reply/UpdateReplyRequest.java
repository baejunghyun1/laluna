package com.laluna.laluna.domain.dto.reply;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateReplyRequest {

    private String replytext;

    private String replyer;
}
