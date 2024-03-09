package com.laluna.laluna.service;

import com.laluna.laluna.domain.dto.reply.*;
import com.laluna.laluna.domain.entity.Board;
import com.laluna.laluna.domain.entity.Reply;
import com.laluna.laluna.repository.BoardRepository;
import com.laluna.laluna.repository.ReplyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyRepository replyRepository;

    private final BoardRepository boardRepository;

    @Transactional
    public CreateReplyResponse replyCreate(CreateReplyRequest requestDTO) {
        Board board = boardRepository.findById(requestDTO.getBoardno())
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + requestDTO.getBoardno()));
        Reply reply = Reply.builder()
                .board(board)
                .replytext(requestDTO.getReplytext())
                .replyer(requestDTO.getReplyer())
                .build();
        Reply saveReply = replyRepository.save(reply);

        return new CreateReplyResponse(
                saveReply.getReplyno(),
                saveReply.getBoard().getBoardno(),
                saveReply.getReplytext(),
                saveReply.getReplyer(),
                saveReply.getBoard().getRegdate(),
                saveReply.getBoard().getModdate());
    }

    public List<ReadReplyResponse> getRepliesByBoardId(Long boardno) {
        List<Reply> replies = replyRepository.findByBoardBoardno(boardno);
        return replies.stream()
                .map(reply -> new ReadReplyResponse(
                        reply.getReplyno(),
                        reply.getBoard().getBoardno(),
                        reply.getReplytext(),
                        reply.getReplyer(),
                        reply.getRegdate(),
                        reply.getModdate()))
                .collect(Collectors.toList());
    }


    @Transactional
    public UpdateReplyResponse replyUpdate(Long replyno, UpdateReplyRequest requestDTO){

        Reply findReply = replyRepository.findById(replyno)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        findReply.update(requestDTO.getReplytext(),requestDTO.getReplyer());

        return new UpdateReplyResponse(
                findReply.getReplyno(),
                findReply.getBoard().getBoardno(),
                findReply.getReplytext(),
                findReply.getReplyer(),
                findReply.getBoard().getRegdate(),
                findReply.getBoard().getModdate());
    }

    @Transactional
    public DeleteReplyResponse replyDelete(Long replyno){

        Reply findReply = replyRepository.findById(replyno)
                .orElseThrow(() -> new EntityNotFoundException("해당 id로 조회된 댓글이 없습니다"));

        replyRepository.delete(findReply);

        return new DeleteReplyResponse(findReply.getReplyno());
    }

}
