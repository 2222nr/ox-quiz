package com.example.oxquiz.controller;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.service.QuizService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping("quiz")
    public String quizList(Model model){
        List<QuizDto> quizDtoList = quizService.QuizAllList();
        model.addAttribute("QuizDto", quizDtoList);
        return "quiz";
    }

    @GetMapping("/quiz/insert")
    public String insertForm(Model model){
        model.addAttribute("quizDto", new QuizDto());
        return "quiz/insert";
    }

    @PostMapping("/quiz/insert")
    public String insertQuiz(@ModelAttribute("quizDto") QuizDto quizDto){
        quizService.insertQuiz(quizDto);
        return "redirect:/quiz";
    }

    @GetMapping("/quiz/{updateId}")
    public String updateForm(@PathVariable("updateId") Long id, Model model){
        QuizDto quizDto = quizService.getOneQuiz(id);
        model.addAttribute("quizDto", quizDto);
        return "quiz/update";
    }

    @PostMapping("/quiz/update")
    public String updateQuiz(@ModelAttribute("quizDto") QuizDto quizDto){
        quizService.updateQuiz(quizDto);
        return "redirect:/quiz";
    }

    @PostMapping("/quiz/delete/{deleteId}")
    public String deleteQuiz(@PathVariable("deleteId") Long id){
        quizService.deleteQuiz(id);
        return "redirect:/quiz";
    }

    @GetMapping("/quiz/play")
    public String playQuiz(Model model){
        QuizDto quizDto = quizService.randomOneQuiz();
//        if(quizDto == null){
//            quizDto.setQuestion("등록된 문제가 없습니다.");
//        }
        model.addAttribute("quizDto", quizDto);
        return "quiz/play";

    }

    @PostMapping("/quiz/play")
    public String answer( @RequestParam("id") Long id,
                          @RequestParam("answer") String answer,
                          Model model) {
        String check = quizService.checkAnswer(id, answer);
        model.addAttribute("check", check);
        return "quiz/check";
    }
}