package com.bigmakk.question_service.service;


import com.bigmakk.question_service.dao.QuestionDao;
import com.bigmakk.question_service.model.Question;
import com.bigmakk.question_service.model.QuestionWrapper;
import com.bigmakk.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions(){
        try{
            List<Question>qlist=new ArrayList<>();
            qlist=questionDao.findAll();
            return new ResponseEntity<>(qlist, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {

        try{
            List<Question>qlist=new ArrayList<>();
            qlist=questionDao.findByCategory(category);;
            return new ResponseEntity<>(qlist, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) {
        try{
          questionDao.save(question);
          return new ResponseEntity<>("Success",HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("failure",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQuestions) {
        List<Integer> questions=questionDao.findRandomQuestionsByCategory(category,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromID(List<Integer> questionIds) {

        List<QuestionWrapper> questionWrappers=new ArrayList<>();
        for(int i:questionIds){
            Question question=questionDao.findById(i).get();
            QuestionWrapper questionWrapper=new QuestionWrapper(question.getId(),question.getDescription(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
            questionWrappers.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int score=0;
        for(Response response:responses){
            Question question=questionDao.findById(response.getId()).get();
            if(response.getAns().equals(question.getCorrectAnswer()))
                score++;



        }

        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
