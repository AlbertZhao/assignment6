package com.zhaoshijie.assignment6.controler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {


    @GetMapping("/")
    public String main(Model model) {

        return "login";
    }


}
