package com.yawn.study.board.service;

import com.yawn.study.board.dto.BoardListResponseDto;
import com.yawn.study.board.dto.BoardPostDto;
import com.yawn.study.board.dto.BoardPostResponseDto;
import com.yawn.study.board.dto.BoardResponseDto;
import com.yawn.study.board.entity.Board;
import com.yawn.study.board.mapper.BoardMapper;
import com.yawn.study.board.repository.BoardRepository;
import com.yawn.study.comment.dto.CommentResponseDto;
import com.yawn.study.comment.entity.Comment;
import com.yawn.study.memeber.entity.Member;
import com.yawn.study.memeber.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BoardService {

    private final BoardMapper boardMapper;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardMapper boardMapper,
                        BoardRepository boardRepository,
                        MemberRepository memberRepository) {
        this.boardMapper = boardMapper;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    public BoardPostResponseDto boardPost(BoardPostDto postDto,
                                          String Email) {
        log.info("보드포스트 서비스 시작");
        log.info("제목: {}, 내용: {}", postDto.getTitle(), postDto.getContent());
        Board board = boardMapper.toBoard(postDto);
        log.info("보드매퍼 적용후 제목: {}, 내용: {}", board.getTitle(), board.getContent());
        Member member = memberRepository.findByEmail(Email)
                .orElseThrow(() -> new IllegalArgumentException("작성자 없음"));
        board.setBoardCreator(member);
        log.info("게시물 게시자 닉네임: {}, 이메일: {}", board.getMember().getNickname(), board.getMember().getEmail());
        board = boardRepository.save(board);
        log.info("게시물 id: {}", board.getId());
        return boardMapper.toBoardPostResponseDto(board);
    }

    public BoardResponseDto boardRead(long boardId) {
        log.info("보드리드 서비스 시작");
        log.info("조회 요청한 보드 id: {}", boardId);

        Board board = boardRepository.findByIdWithAll(boardId)
                .orElseThrow(() -> new NoSuchElementException("게시글 존재하지 않음"));

        // 오류관련 확인이 끝나서 간소화
//        log.info("Board 작성자: {}", board.getMember() != null ? board.getMember().getNickname() : "null");
//        if (board.getComments() != null) {
//            for (Comment comment : board.getComments()) {
//                log.info("DB 로그 댓글 ID: {}, 내용: {}, 작성자: {}, 소속 게시판 ID: {}",
//                        comment.getId(),
//                        comment.getContent(),
//                        comment.getMember() != null ? comment.getMember().getNickname() : "null",
//                        comment.getBoard() != null ? comment.getBoard().getId() : "null");
//            }
//        } else {
//            log.info("댓글 리스트가 null입니다.");
//        }

        log.info("매핑 전 게시글 ID: {}, 제목: {}, 작성자: {}",
                board.getId(),
                board.getTitle(),
                board.getMember() != null ? board.getMember().getNickname() : "null");

         BoardResponseDto responseDto = boardMapper.toBoardResponseDto(board);

//        if (responseDto.getComments() != null) {
//            for (CommentResponseDto commentDto : responseDto.getComments()) {
//                log.info("매핑 이후 댓글 ID: {}, 댓글 작성자: {}, 소속 게시글 ID: {}, 내용: {}",
//                        commentDto.getId(),
//                        commentDto.getNickname() != null ? commentDto.getNickname() : "닉네임 없음",
//                        commentDto.getBoardId() != null ? commentDto.getBoardId() : "게시글 ID 없음",
//                        commentDto.getContent());
//            }
//        } else {
//            log.info("댓글 정보 없음");
//        }

        CommentResponseDto first = responseDto.getComments().get(0);
        log.info("매핑 이후 댓글 ID: {}, 내용: {}, 작성자: {}, 게시글 ID: {}",
                first.getId(),
                first.getContent(),
                first.getNickname(),
                first.getBoardId());

        return responseDto;
    }

    public List<BoardListResponseDto> findAllBoards() {
        return boardRepository.findAll().stream()
                .map(boardMapper::toBoardListDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public BoardPostResponseDto boardEdit(BoardPostDto postDto,
                                          String email,
                                          long boardId) {
        log.info("보드에딧 서비스 시작");
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("게시글 존재하지 않음"));

        if (!email.equals(board.getMember().getEmail())) {
            throw new IllegalArgumentException("작성자만 게시글을 수정할 수 있습니다");
        }

        board.edit(postDto.getTitle(), postDto.getContent());

        /*
        아래 처럼 처리하면 board 를 새 객체로 덮어쓰기 때문에 영속성이 날아감
        심지어 새글을 쓰는 것이기 때문에 id 도 바뀌고 원글은 수정도 안 됨
         */
//        board = boardMapper.toBoard(postDto);
//        boardRepository.save(board);

        return boardMapper.toBoardPostResponseDto(board);
    }

    public void boardDelete(String email,
                            long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NoSuchElementException("게시글 존재하지 않음"));

        if (!email.equals(board.getMember().getEmail())) {
            throw new IllegalArgumentException("작성자만 게시글을 삭제할 수 있습니다");
        }

        boardRepository.deleteById(boardId);
    }

}
