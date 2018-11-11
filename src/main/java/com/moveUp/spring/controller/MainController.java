package com.moveUp.spring.controller;

import com.moveUp.spring.dao.ActivityDao;
import com.moveUp.spring.dao.EventDao;
import com.moveUp.spring.dao.OpinionDao;
import com.moveUp.spring.dao.UserDao;
import com.moveUp.spring.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class MainController
{
    @Autowired
    private EventDao eventDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private OpinionDao opinionDao;


    @GetMapping("/")
    public String index()
    {
        return "index";
    }
}
