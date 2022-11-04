package com.nighthawk.spring_portfolio.mvc.questions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // annotation to simplify the creation of RESTful web services
@RequestMapping("/api/quizold")  // all requests in file begin with this URI
public class QuizControllerOld {
    @Autowired
    private QuizRepositoryOld repository;

    /* GET List of Jokes
     * @GetMapping annotation is used for mapping HTTP GET requests onto specific handler methods.
     */
    @GetMapping("/")
    public ResponseEntity<List<QuizOld>> getQuiz() {
        // ResponseEntity returns List of Jokes provide by JPA findAll()
        return new ResponseEntity<>( repository.findAll(), HttpStatus.OK);
    }

    /* Update Like
     * @PutMapping annotation is used for mapping HTTP PUT requests onto specific handler methods.
     * @PathVariable annotation extracts the templated part {id}, from the URI
     */
    @PutMapping("/choiceA/{id}")
    public ResponseEntity<QuizOld> setLike(@PathVariable long id) {
        /* 
        * Optional (below) is a container object which helps determine if a result is present. 
        * If a value is present, isPresent() will return true
        * get() will return the value.
        */
        Optional<QuizOld> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            QuizOld question = optional.get();  // value from findByID
            question.setChoiceA(question.getChoiceA()+1); // increment value
            repository.save(question);  // save entity
            return new ResponseEntity<>(question, HttpStatus.OK);  // OK HTTP response: status code, headers, and body
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Failed HTTP response: status code, headers, and body
    }

    /*  NOTE: COMMENTED OUT FOR NOW TO SIMPLIFY THINGS
    Update Jeer
     
    @PutMapping("/choiceB/{id}")
    public ResponseEntity<Jokes> setJeer(@PathVariable long id) {
        Optional<Quiz> optional = repository.findById(id);
        if (optional.isPresent()) {  // Good ID
            Quiz question = optional.get();
            question.setChoiceB(question.getChoiceB()+1);
            repository.save(question);
            return new ResponseEntity<>(question, HttpStatus.OK);
        }
        // Bad ID
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    */
}



