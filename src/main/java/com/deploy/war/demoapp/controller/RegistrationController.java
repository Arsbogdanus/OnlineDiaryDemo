package com.deploy.war.demoapp.controller;

import com.deploy.war.demoapp.DemoAppValues;
import com.deploy.war.demoapp.models.Person;
import com.deploy.war.demoapp.repository.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class RegistrationController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/registration")
    public String registrationGetMapping(Model model) {
        model.addAttribute("person", new Person());
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationPostMapping(
            @ModelAttribute("person") @Valid Person person,
            BindingResult bindingResult
    ) {
        String email = person.getEmail();
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (!DemoAppValues.isValidEmail(email)) {
            bindingResult.rejectValue("email", "error.person", "Email should be valid");
            return "registration";
        } else {
            for (Person personForEach : personRepository.findAll()) {
                System.out.println(personForEach.getEmail());
                if (Objects.equals(personForEach.getEmail(), email)) {
                    bindingResult.rejectValue("email", "error.person", "Email already exists");
                    System.out.println("personForEach");
                    return "registration";
                }
            }
            personRepository.save(person);
            return "redirect:/login";
        }
    }
}