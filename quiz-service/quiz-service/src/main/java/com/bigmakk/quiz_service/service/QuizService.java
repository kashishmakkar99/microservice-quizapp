package com.bigmakk.quiz_service.service;


import com.bigmakk.quiz_service.client.QuestionClient;
import com.bigmakk.quiz_service.dao.QuizDao;
import com.bigmakk.quiz_service.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionClient questionClient;
//    @Autowired
//    QuestionDao questionDao;
    public ResponseEntity<String> createQuiz(String category, int num, String title) {
        try{
            //Rest Template
            List<Integer> questions=questionClient.getQuestionForQuiz(category,num).getBody();
            Quiz quiz=new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity<>("success",HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        Quiz quiz=quizDao.findById(id).get();
        List<Integer> questionIds=quiz.getQuestions();
        ResponseEntity<List<QuestionWrapper>> questionsForUser=questionClient.getQuestionsFromId(questionIds);

        return questionsForUser;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {

        ResponseEntity<Integer>score=questionClient.getScore(responses);

        return score;
    }

}
