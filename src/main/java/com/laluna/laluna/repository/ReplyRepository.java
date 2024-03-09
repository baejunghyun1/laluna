package com.laluna.laluna.repository;

import com.laluna.laluna.domain.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByBoardBoardno(Long boardno);
}


