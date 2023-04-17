package org.koreait.exam03.controllers;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.koreait.controllers") // controllers 하위에 있는 오류를 찾아냄.
public class CommonController {

    @ExceptionHandler(RuntimeException.class)
    public String errorHandler(RuntimeException e, Model model){

        e.printStackTrace();

        model.addAttribute("message", e.getMessage());
        return "error/common";
    }
}
