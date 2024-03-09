//package com.laluna.laluna.service;
//
//import com.laluna.laluna.config.MyUserDetails;
//import com.laluna.laluna.domain.dto.board.CreateBoardRequest;
//import com.laluna.laluna.domain.dto.board.CreateBoardResponse;
//import com.laluna.laluna.domain.dto.board.ReadBoardResponse;
//import com.laluna.laluna.domain.dto.board.UpdateBoardRequest;
//import com.laluna.laluna.domain.entity.Board;
//import com.laluna.laluna.domain.entity.Member;
//import com.laluna.laluna.repository.BoardRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.Arrays;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.when;
//
//
//@ExtendWith(MockitoExtension.class)
//public class BoardServiceTest {
//
//    @Mock
//    private BoardRepository boardRepository;
//
//    @InjectMocks
//    private BoardService boardService;
//
//    private Board saveBoard;
//    private Board board;
//    private CreateBoardRequest createBoardRequest;
//    private UpdateBoardRequest updateBoardRequest;
//
//    @BeforeEach
//    void setup() {
//        board = new Board(1L, null, "제목", "내용", "카테고리",null);
//        saveBoard = new Board(2L, null, "title", "content", "category", null);
//        createBoardRequest = new CreateBoardRequest(null, "title", "content", "category");
//        updateBoardRequest = new UpdateBoardRequest(null, "change title", "change content", "change category");
//    }
//
//    @Test
//    @DisplayName("게시글 작성 테스트")
//    public void testBoardCreate() {
//        // Given
//        MyUserDetails userDetails = new MyUserDetails(
//                "username", // 사용자 이름
//                "password", // 비밀번호
//                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")), // 권한 목록
//                1L, // mnum
//                "mphone", // 전화번호
//                "address", // 주소
//                "email" // 이메일
//        );
//
//        given(boardRepository.save(any())).willReturn(board);
//
//        // When
//        CreateBoardResponse createBoardResponse = boardService.boardCreate(userDetails, createBoardRequest);
//
//        // Then
//        assertThat(createBoardResponse.getBoardno()).isEqualTo(1L);
//        assertThat(createBoardResponse.getLink()).isEqualTo(null);
//        assertThat(createBoardResponse.getTitle()).isEqualTo("제목");
//        assertThat(createBoardResponse.getContent()).isEqualTo("내용");
//        assertThat(createBoardResponse.getCategory()).isEqualTo("카테고리");
//    }
//
//    @Test
//    @DisplayName("Boardid로 게시글 조회하는 테스트")
//    void read() {
//        when(boardRepository.findById(any())).thenReturn(Optional.of(saveBoard));
//
//        ReadBoardResponse readBoardResponse = boardService.boardRead(saveBoard.getBoardid());
//
//        assertThat(readBoardResponse.getBoardid()).isEqualTo(saveBoard.getBoardid());
//        assertThat(readBoardResponse.getTitle()).isEqualTo(saveBoard.getTitle());
//    }
//
//    @Test
//    @DisplayName("전체 게시글 조회 기능 테스트")
//    void readAll() {
//        Pageable pageable = PageRequest.of(0,5);
//        List<Board> boards = Arrays.asList(board,saveBoard);
//        Page<Board> boardPage = new PageImpl<>(boards, pageable, boards.size());
//
//        given(boardRepository.findAll(pageable)).willReturn(boardPage);
//
//        Page<ReadBoardResponse> responses = boardService.boardList(pageable);
//
//        assertThat(responses.getContent()).hasSize(2);
//        assertThat(responses.getContent().get(0).getTitle()).isEqualTo("제목");
//        assertThat(responses.getContent().get(0).getContent()).isEqualTo("내용");
//        assertThat(responses.getContent().get(1).getTitle()).isEqualTo("title");
//        assertThat(responses.getContent().get(1).getContent()).isEqualTo("content");
//
//    }
//}
