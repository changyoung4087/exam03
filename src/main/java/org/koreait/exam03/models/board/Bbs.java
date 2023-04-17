package org.koreait.exam03.models.board;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Data
public class Bbs {
    private Long id;
    private String bbsTitle;
    private String bbsContent;
    private LocalDateTime regDt;
    private LocalDateTime updDt;

}
