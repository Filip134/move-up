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

    User user;
    Activity activity;
    Event event;


    @GetMapping("/")
    public String index()
    {
        user = new User("login 1", "password 1");
        activity = new Activity(false, "Activity");
        event = new Event("Something", "Something something", "Somewhere", 8,
                Advancement.INTERMEDIATE, 2018, 8, 15, 16, 30, user, activity);

        userDao.addUser(user);
        activityDao.addActivity(activity);
        eventDao.addEvent(event);

        eventDao.addUserToEvent(user, event);

        return "index";
    }

    @GetMapping("/delete")
    public String removeUser()
    {
        eventDao.removeUserFromEvent(user, event);

        return "index";
    }
}
