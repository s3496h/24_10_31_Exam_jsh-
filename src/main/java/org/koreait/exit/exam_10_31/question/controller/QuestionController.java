package org.koreait.exit.exam_10_31.question.controller;

import org.koreait.exit.exam_10_31.question.service.PromptAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuestionController {
    @Autowired
    private PromptAIService promptAIService;

    @GetMapping("/board")
    public String showBoard() {
        return "prompt_form";
    }

    @PostMapping("/ask")
    public String askQuestion(@RequestParam("userQuestion") String userQuestion, Model model) {
        String systemAnswer = promptAIService.getAutoResponse(userQuestion);
        model.addAttribute("userQuestion", userQuestion);
        model.addAttribute("systemAnswer", systemAnswer);
        return "prompt_form";
    }
}