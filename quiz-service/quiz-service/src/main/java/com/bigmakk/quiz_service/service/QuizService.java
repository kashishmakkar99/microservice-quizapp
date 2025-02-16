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
//     Optional<Quiz> q= quizDao.findById(id);
//     List<Question> questionsFromDb=q.get().getQuestions();
     List<QuestionWrapper> questionsForUser=new ArrayList<>();
//     for(Question qu:questionsFromDb){
//         QuestionWrapper qw=new QuestionWrapper(qu.getId(),qu.getDescription(), qu.getOption1(), qu.getOption2(),qu.getOption3(),qu.getOption4());
//         questionsForUser.add(qw);
//     }
     return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz=quizDao.findById(id).get();
//        List<Question> questions=quiz.getQuestions();
        int score=0;
        int i=0;
//        for(Response response:responses){
//            if(response.getAns().equals(questions.get(i).getCorrectAnswer()))
//                score++;
//
//            i++;
//
//        }

        return new ResponseEntity<>(score,HttpStatus.OK);
    }

}
