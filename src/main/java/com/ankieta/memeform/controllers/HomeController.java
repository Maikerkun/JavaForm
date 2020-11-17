package com.ankieta.memeform.controllers;

import com.ankieta.memeform.domain.Answers;
import com.ankieta.memeform.repositories.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.SQLException;

@Controller
public class HomeController {
    @Autowired
    private AnswerRepository ansRepo;

    @RequestMapping("/")
    public String home(){

        return "index";
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        Answers answers = new Answers();
        model.addAttribute("answers", answers);

        return "form";
    }

    @PostMapping("/form")
    public String submitForm(@ModelAttribute("answers") Answers answers) throws Exception {
        ansRepo.addAnswers(answers);

        return "success";
    }
}
