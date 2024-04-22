package com.example.oxquiz.service;

import com.example.oxquiz.dto.QuizDto;
import com.example.oxquiz.entity.Quiz;
import com.example.oxquiz.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }
    public List<QuizDto> QuizAllList() {
        return quizRepository.findAll()
                .stream()
                .map(x -> QuizDto.fromQuizEntity(x))
                .toList();
    }

    public void insertQuiz(QuizDto quizDto) {
        Quiz quiz = quizDto.fromQuizDto(quizDto);
        quizRepository.save(quiz);
    }

    public QuizDto getOneQuiz(Long id) {
        return quizRepository.findById(id).map(x -> QuizDto.fromQuizEntity(x)).orElse(null);
    }

    public void updateQuiz(QuizDto quizDto) {
        Quiz quiz = quizDto.fromQuizDto(quizDto);
        quizRepository.save(quiz);
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }

    public QuizDto randomOneQuiz() {
        // 1.
//        List<Quiz> quizList = quizRepository.findAll();
//        Long id = (long) (Math.random() * quizList.toArray().length+1);
//        QuizDto quizDto = quizRepository.findById(id)
//                .map(x -> QuizDto.fromQuizEntity(x)).orElse(null);
//         2.
        QuizDto quizDto = QuizDto.fromQuizEntity(quizRepository.randomOne());
        return quizDto;
    }

    public String checkAnswer(Long id, String answer) {
        QuizDto quizDto = quizRepository.findById(id)
                .map(x -> QuizDto.fromQuizEntity(x)).orElse(null);
        if(answer.equals(quizDto.getAnswer())){
            return "정답입니다.";
        } else { return "오답입니다.";}
    }
}
