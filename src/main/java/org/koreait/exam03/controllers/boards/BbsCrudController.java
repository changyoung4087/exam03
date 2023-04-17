package org.koreait.exam03.controllers.boards;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.exam03.models.board.Bbs;
import org.koreait.exam03.models.board.BbsDAO;
import org.koreait.exam03.models.board.BbsInsertService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bbs")
@RequiredArgsConstructor
public class BbsCrudController {

    private final BbsInsertService insertService;
    private final BbsDAO bbsDao;

    /** 등록 GET */
    @GetMapping("/bbsInsert")
    public String bbsInsert(Model model){
        BbsParam bbsParam = new BbsParam();

        model.addAttribute("bbsParam", bbsParam);

        return "bbs/bbsInsert";
    }

    /** 등록 POST */
    @PostMapping("/bbsInsert")
    public String bbsInsertPs(@Valid BbsParam bbsParam, Errors errors){
        if(errors.hasErrors()){

            return "bbs/bbsInsert";
        }
        insertService.dataInsert(bbsParam);
        return "redirect:/bbs";
    }

    /** 게시글 상세 GET */
    @GetMapping("/bbsInfo/{id}")
    public String bbsInfo(@PathVariable Long id, Model model){

        Bbs bbs = bbsDao.bbsInfo(id);
        model.addAttribute("bbs", bbs);
        return "bbs/bbsInfo";
    }

    /** 게시글 수정 & 삭제 POST */
    @PostMapping("/bbsInfo/{id}")
    public String bbsUpdate(@Valid BbsParam bbsParam, Errors errors, @RequestParam(name="button") String button, @PathVariable Long id){

        if(errors.hasErrors()){

            return "bbs/bbsInfo";
        }
        if(button.equals("D")){
            insertService.dataDelete(bbsParam);
        }else if(button.equals("U")){
            insertService.dataUpdate(bbsParam);
        }

        return "redirect:/bbs";
    }
}
