package com.moveUp.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class MainController
{
    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    @GetMapping("/index")
    public String indexPath()
    {
        return "redirect:/";
    }
}

