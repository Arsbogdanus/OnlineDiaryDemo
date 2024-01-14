package com.deploy.war.demoapp.controller;

import com.deploy.war.demoapp.DemoAppValues;
import com.deploy.war.demoapp.models.Person;
import com.deploy.war.demoapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    private PersonRepository personRepository;
    Iterable<Person> people;

    @GetMapping("/login")
    public String loginGetMapping() {
        return "login";
    }

    @PostMapping("/login")
    public String loginPostMapping(@RequestParam String email, @RequestParam String password, Model model) {
        people = personRepository.findAll();

        for (Person person : people) {
            if (person.getEmail().equals(email) && person.getPassword().equals(password)) {
                DemoAppValues.CURRENT_PERSON_ID = person.getId();
                System.out.println(DemoAppValues.CURRENT_PERSON_ID);
                return "redirect:/main";
            }
        }
        return "login";
    }


}
