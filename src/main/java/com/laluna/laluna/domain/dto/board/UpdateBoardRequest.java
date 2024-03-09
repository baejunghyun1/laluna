package com.laluna.laluna.domain.dto.board;

import com.laluna.laluna.domain.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBoardRequest {

    private List<Photo> link;  //이미지 링크

    //작성자 id= 세션값으로 받아오기

    private String title;   //게시글 제목

    private String content; //게시글 내용

    private String category; //게시글 카테고리 (건강, 팁, 일상)

}
