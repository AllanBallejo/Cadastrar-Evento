package br.com.allan.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = true) String name,
                           @RequestParam(name = "lastName", required = true) String lastName,
                           Model model) {
        model.addAttribute("name", name);
        model.addAttribute("lastName", lastName);
        return "greeting";
    }

}
