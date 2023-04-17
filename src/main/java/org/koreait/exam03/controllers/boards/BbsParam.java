package org.koreait.exam03.controllers.boards;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BbsParam {

    private Long id;
    @NotBlank
    private String bbsTitle;
    @NotBlank
    private String bbsContent;
}
