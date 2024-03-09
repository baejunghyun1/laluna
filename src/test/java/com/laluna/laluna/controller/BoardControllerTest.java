package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.board.*;
import com.laluna.laluna.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BoardControllerTest {
    @Mock
    BoardService boardService;

    @InjectMocks
    BoardController boardController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }

    @Test
    public void createBoardTest() throws Exception {
        // Given
        CreateBoardRequest requestDTO = new CreateBoardRequest(null, "title", "content", "category");
        CreateBoardResponse responseDTO = new CreateBoardResponse(1L, null, "title", "content", "category", null, null);
        RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

        // When
        when(boardService.boardCreate(any(MyUserDetails.class), any(CreateBoardRequest.class))).thenReturn(responseDTO);

        // Then
        mockMvc.perform(post("/boards/create")
                        .param("title", requestDTO.getTitle())
                        .param("content", requestDTO.getContent())
                        .param("category", requestDTO.getCategory())
                        .flashAttr("board", responseDTO))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/list"));
    }

    @Test
    public void testReadBoard() throws Exception {
        // Given
        Long boardid = 1L;

        // When
        given(boardService.boardRead(boardid)).willReturn(new ReadBoardResponse());

        // Then
        mockMvc.perform(get("/boards/" + boardid))
                .andExpect(status().isOk())
                .andExpect(view().name("boardview"));
    }

    @Test
    public void testUpdateBoard() throws Exception {
        // Given
        Long boardid = 1L;
        UpdateBoardRequest requestDTO = new UpdateBoardRequest();

        // When
        given(boardService.boardUpdate(Mockito.anyLong(), Mockito.any())).willReturn(new UpdateBoardResponse());

        // Then
        mockMvc.perform(put("/boards/" + boardid)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/boardview/" + boardid));
    }

    @Test
    public void testDeleteBoard() throws Exception {
        // Given
        Long boardid = 1L;
        // When
        given(boardService.boardDelete(Mockito.anyLong())).willReturn(new DeleteBoardResponse());

        // Then
        mockMvc.perform(delete("/boards/" + boardid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/view/list"));
    }

    @Test
    public void testListBoards() throws Exception {
        // Given
        Page<ReadBoardResponse> boardPage = new PageImpl<>(Collections.singletonList(new ReadBoardResponse()));
        // When
        given(boardService.boardList(Mockito.any())).willReturn(boardPage);

        // Then
        mockMvc.perform(get("/boards/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("boardList"));
    }
}
