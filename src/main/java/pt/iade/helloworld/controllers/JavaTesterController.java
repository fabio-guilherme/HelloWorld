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
        /// *
        String name = "Fabio Guilherme";
        int number = 40001842;
        String nationality = "Brazilian";
        boolean isFootballFan = true;
        String favouriteFootballClub = "Fluminense";
        // */
        /*
         * String name = "Cristiano Ronaldo";
         * int number = 7;
         * String nationality = "Portuguese";
         * boolean isFootballFan = false;
         * String favouriteFootballClub = "";
         */
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

    @GetMapping(path = "/access/{student}/{covid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getGreeting(@PathVariable("student") boolean isStudent,
            @PathVariable("covid") boolean hasCovid) {
        return (isStudent && !hasCovid);
    }

    @GetMapping(path = "/required/{student}/{temperature}/{classType}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean getRequired(@PathVariable("student") boolean isStudent,
            @PathVariable("temperature") double temperature,
            @PathVariable("classType") String type) {
        return (isStudent && type.equals("presential") && temperature >= 34.5 && temperature <= 37.5);
    }

    final static private double grades[] = { 10.5, 12, 14.5 };
    final static private String ucs[] = { "FP", "POO", "BD" };

    @GetMapping(path = "/grades/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getAverage() {
        double sum = 0;

        for (double grade : grades) {
            sum += grade;
        }
        /*
         * for (int i = 0; i < grades.length; i++) {
         * double grade = grades[i];
         * sum += grade;
         * }
         */

        return sum / grades.length;
    }

    @GetMapping(path = "/units/{name}/grade", produces = MediaType.APPLICATION_JSON_VALUE)
    public double getGradeByUnitName(@PathVariable("name") String name) {
        for (int i = 0; i < ucs.length; i++) {
            if (ucs[i].equals(name)) {
                return grades[i];
            }
        }
        return -1;
    }

    private ArrayList<CurricularUnit> units = new ArrayList<CurricularUnit>();

    @PostMapping(path = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public CurricularUnit saveUnit(@RequestBody CurricularUnit unit) {
        logger.info("Added unit " + unit.getName());
        units.add(unit);
        return unit;
    }

    @GetMapping(path = "/units", produces = MediaType.APPLICATION_JSON_VALUE)
    public ArrayList<CurricularUnit> getUnits() {
        logger.info("Get " + units.size() + " Units");
        return units;
    }
}