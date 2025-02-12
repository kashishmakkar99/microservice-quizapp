package com.bigmakk.question_service.service;


import com.bigmakk.question_service.dao.QuestionDao;
import com.bigmakk.question_service.model.Question;
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
}
