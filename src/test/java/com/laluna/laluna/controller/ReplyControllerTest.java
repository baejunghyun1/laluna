package com.laluna.laluna.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laluna.laluna.controller.ReplyController;
import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.service.BoardService;
import com.laluna.laluna.service.ReplyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ReplyControllerTest {

    @Mock
    ReplyService replyService;

    @InjectMocks
    ReplyController replyController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(replyController).build();
    }

    @Test
    public void testCreateReply() throws Exception {
        // Given
        CreateReplyRequest requestDTO = new CreateReplyRequest("댓글 내용", "작성자", 1L);
        CreateReplyResponse response = new CreateReplyResponse(1L, 1L, "댓글 내용", "작성자", null, null);

        //When
        given(replyService.replyCreate(any())).willReturn(response);
        //Then
        mockMvc.perform(post("/boards/boardview")
                        .param("replytext", requestDTO.getReplytext())
                        .param("replyer", requestDTO.getReplyer()))
                .andExpect(status().is3xxRedirection());
    }


//    @Test
//    public void testViewReply() throws Exception {
//        // Given
//        Long replynum = 1L;
//        ReadReplyResponse response = new ReadReplyResponse(1L,null, "댓글 내용", "작성자", null, null);
//        when(replyService.replyRead(any())).thenReturn(response);
//
//        // When & Then
//        mockMvc.perform(get("/boards/replylist/{replynum}", replynum))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(model().attributeExists("reply"))
//                .andExpect(view().name("boardview"))
//                .andExpect(model().attribute("reply", response))
//                .andDo(print());
//    }

    @Test
    public void testUpdateReply() throws Exception {
        // Given
        Long replynum = 1L;
        UpdateReplyRequest requestDTO = new UpdateReplyRequest("수정된 댓글 내용", "작성자");

        // When & Then
        mockMvc.perform(put("/api/replies/{replynum}", replynum)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(requestDTO)))
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("message", "ㅁ댓글이 성공적으로 수정되었습니다."))
                .andExpect(redirectedUrl("/view/boardview"))
                .andExpect(model().hasNoErrors());

    }
}

//    @Test
//    public void testdeleteReply() throws Exception{
//        //given
//        Long replynum = 1L;
//        DeleteReplyResponse response = new DeleteReplyResponse(replynum);
//        //when
//        given(replyService.deleteReply(any())).willReturn(response);
//    }
//}