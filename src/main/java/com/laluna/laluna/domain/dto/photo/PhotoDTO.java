package com.laluna.laluna.domain.dto.photo;

import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {


    private Long photono;

    private Member member;

    private String link;

    private Board board;

}
