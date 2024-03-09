package com.laluna.laluna.controller;

import com.laluna.laluna.config.MyUserDetails;
import com.laluna.laluna.domain.dto.board.*;
import com.laluna.laluna.domain.dto.reply.ReadReplyResponse;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.repository.ReplyRepository;
import com.laluna.laluna.service.BoardService;
import com.laluna.laluna.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @Autowired

    private ReplyService replyService;

    @GetMapping("/register")
    public void createGET() {
    }
    @PostMapping("/register")
    public String createBoard(@AuthenticationPrincipal MyUserDetails userDetails,
                              @ModelAttribute CreateBoardRequest requestDTO,
                              RedirectAttributes redirectAttributes) {
        CreateBoardResponse responseDTO = boardService.boardCreate(userDetails, requestDTO);

        redirectAttributes.addFlashAttribute("board", responseDTO);
        return "redirect:/boards/list";    //글작성후, 리다이렉트 해서 해당 페이지에 정보를 전달할 때 사용
    }
    //      model.addAttribute("board", responseDTO);
    //      return "boardView";   //해당 데이터는 현재 요청에서 처리되는 뷰에서만 사용할 수 있고 사용되면 더이상 사용불가

    @GetMapping("/read/{boardno}")
    public String readBoard(@PathVariable("boardno") Long boardno, Model model) {
        ReadBoardResponse responseDTO = boardService.boardRead(boardno);
        List<ReadReplyResponse> responseList = replyService.getRepliesByBoardId(boardno);
        model.addAttribute("replies", responseList);
        model.addAttribute("board", responseDTO);
        return "boards/boardview";
    }
    //Spring MVC 는 뷰 리졸버(view resolver)를 사용하여 뷰 이름을 실제 뷰로 변환한다.
    //Thymeleaf 를 사용하는 경우 ThymeleafViewResolver 가 뷰 리졸버로 사용되며, 뷰 이름을 템플릿 파일의 경로로 변환한다.
    // 기본 설정에서는 src/main/resources/templates 디렉토리가 템플릿 파일의 기본 위치이다.

    @GetMapping("/update/{boardno}")
    public String modifyBoard(@PathVariable("boardno") Long boardno, Model model) {
        ReadBoardResponse responseDTO = boardService.boardRead(boardno);
        List<ReadReplyResponse> responseList = replyService.getRepliesByBoardId(boardno);
        model.addAttribute("replies", responseList);
        model.addAttribute("board", responseDTO);
        return "boards/boardupdate";
    }
    @PutMapping("/update/{boardno}")
    public String updateBoard(@PathVariable Long boardno,
                              @ModelAttribute UpdateBoardRequest requestDTO,
                              RedirectAttributes redirectAttributes) {
        UpdateBoardResponse responseDTO = boardService.boardUpdate(boardno, requestDTO);
        redirectAttributes.addFlashAttribute("board", responseDTO);
        return "redirect:/boards/read/" + boardno;
    }

    @DeleteMapping("/delete/{boardno}")
    public String deleteBoard(@PathVariable Long boardno, RedirectAttributes redirectAttributes) {
        DeleteBoardResponse responseDTO = boardService.boardDelete(boardno);
        redirectAttributes.addFlashAttribute("board", responseDTO);
        //게시글을 삭제후에 boardno 해당하는 값을 받아와서 메세지로 띄울 경우가아니면  삭제해도 무방할듯싶다.
        return "redirect:/view/list";
    }

    @GetMapping("/list")
    public String listBoards(@RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 9, Sort.by("boardno").descending());
        Page<ReadBoardResponse> boardPage = boardService.boardList(pageable);

        int nowPage = boardPage.getNumber() + 1;
        int totalPages = boardPage.getTotalPages();
        int pageSize = 10;

        int groupNumber = (nowPage - 1) / pageSize;

        int startPage = groupNumber * pageSize + 1;
        int endPage = Math.min(startPage + pageSize - 1, totalPages);

        model.addAttribute("boards", boardPage);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "/boards/boardlist";
    }
//    @GetMapping("/category/{category}")
//    public String getPostsByCategory(@PathVariable String category, Model model) {
//        List<ReadBoardResponse> boards = boardService.getBoardsByCategory(category);
//        model.addAttribute("boards", boards);
//        return "/boards/boardlist";
//    }

    @GetMapping("/category/{category}")
    public String getBoardsByCategory(@PathVariable String category, @RequestParam(defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 9, Sort.by("boardno").descending());
        Page<ReadBoardResponse> pagedBoards = boardService.getBoardsListByCategory(category, pageable);

        int nowPage = pagedBoards.getNumber() + 1;
        int totalPages = pagedBoards.getTotalPages();
        int pageSize = 10;

        int groupNumber = (nowPage - 1) / pageSize;

        int startPage = groupNumber * pageSize + 1;
        int endPage = Math.min(startPage + pageSize - 1, totalPages);

        model.addAttribute("categoryPage", pagedBoards);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "/boards/boardlist";
    }

@GetMapping("/board/{title}")
public String getBoardsByTitle(@PathVariable String title, Model model) {
    List<Board> boards = boardService.getBoardsByTitle(title);
    model.addAttribute("boards", boards);
    return "/boards/boardlist";
}
}
