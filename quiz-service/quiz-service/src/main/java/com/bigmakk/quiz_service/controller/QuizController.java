package com.bigmakk.quiz_service.controller;


import com.bigmakk.quiz_service.model.QuestionWrapper;
import com.bigmakk.quiz_service.model.QuizDTO;
import com.bigmakk.quiz_service.model.Response;
import com.bigmakk.quiz_service.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){

        return quizService.createQuiz(quizDTO.getCategory(),quizDTO.getNum(),quizDTO.getTitle());
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id ){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id,@RequestBody List<Response>responses ){


        return quizService.calculateResult(id,responses);

    }
}
