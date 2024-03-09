package com.laluna.laluna.domain.dto.board;

import com.laluna.laluna.domain.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBoardResponse {

    private Long boardno;

    private List<Photo> link;  //이미지 링크

    private String title;   //게시글 제목

    private String content; //게시글 내용

    private String category; //게시글 카테고리 (건강, 팁, 일상)

    private LocalDateTime regdate;

    private LocalDateTime moddate;



}
