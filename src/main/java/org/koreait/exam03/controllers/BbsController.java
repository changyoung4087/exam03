package org.koreait.exam03.controllers;

import lombok.RequiredArgsConstructor;
import org.koreait.exam03.models.board.Bbs;
import org.koreait.exam03.models.board.BbsDAO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BbsController {

    private final BbsDAO bbsDao;

    @GetMapping("/bbs")
    public String bbs(Model model){

        List<Bbs> bbsList = new ArrayList<>();
        bbsList = bbsDao.bbsList();
        model.addAttribute("bbsList", bbsList);

        return "bbs";
    }
}
