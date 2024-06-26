package com.example.oxquiz.dto;

import com.example.oxquiz.entity.Quiz;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuizDto {
    private Long id;

    @NotBlank(message = "내용은 공백일 수 없습니다.")
    private String question;

    @NotBlank(message = "정답을 선택하세요.")
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