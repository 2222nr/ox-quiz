package com.example.oxquiz.dto;

import com.example.oxquiz.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizDto {
    private Long id;
    private String question;
    private String answer;
    private String writer;

    public static QuizDto fromQuizEntity(Quiz quiz){
        String answer;
        if(quiz.getAnswer() == 0) {
            answer = "O";
        } else { answer = "X";}
        return new QuizDto(
                quiz.getId(), quiz.getQuestion(), answer, quiz.getWriter());
    }

    public Quiz fromQuizDto(QuizDto dto){
        int answer;
        if(dto.getAnswer().equals("O")) {
            answer = 0;
        } else { answer = 1;}
        Quiz quiz = new Quiz();
        quiz.setId(dto.getId());
        quiz.setQuestion(dto.getQuestion());
        quiz.setAnswer(answer);
        quiz.setWriter(dto.getWriter());
        return quiz;
    }

}