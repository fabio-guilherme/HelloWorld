package pt.iade.helloworld.controllers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pt.iade.helloworld.models.CurricularUnit;

@RestController
@RequestMapping(path = "/api/java/tester/")

public class JavaTesterController {

    private Logger logger = LoggerFactory.getLogger(JavaTesterController.class);

    @GetMapping(path = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAuthor() {
        logger.info("Sending the author of this code");
        /* 
        String name = "Fabio Guilherme";
        int number = 40001842;
        String nationality = "Brazilian";
        boolean isFootballFan = true;
        String favouriteFootballClub = "Fluminense";
        */
        ///*
        String name = "Cristiano Ronaldo";
        int number = 7;
        String nationality = "Portuguese";
        boolean isFootballFan = false;
        String favouriteFootballClub = "";
        //*/
        String res = "";

        res += "Done by " + name + " with number " + number + "\n";
        res += "I am " + nationality + " and ";
        if (isFootballFan) {
            res += "I am a fan of football.\n";
            res += "My favorite club is " + favouriteFootballClub + ".\n";
        } else {
            res += "not a fan of football.\n";
        }

        return res;
    }

    @GetMapping(path = "/access/{student}/{covid}", produces= MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent, 
            @PathVariable("covid") boolean hasCovid) {
        return (isStudent && !hasCovid);
    }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}", produces= MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent, 
            @PathVariable("temperature") double temperature,
            @PathVariable("classType") String type) {
        return (isStudent && type.equals("presential") && temperature >= 34.5 && temperature <= 37.5);
    }

}