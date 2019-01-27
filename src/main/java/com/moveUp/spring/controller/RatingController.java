package com.moveUp.spring.controller;

import com.moveUp.spring.model.Event;
import com.moveUp.spring.model.User;
import com.moveUp.spring.service.EventService;
import com.moveUp.spring.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RatingController
{
    @Autowired
    private RatingService ratingService;
    @Autowired
    private EventService eventService;

    @PostMapping("/rating")
    public String rate(HttpServletRequest request, HttpSession session)
    {
        User user = (User)session.getAttribute("user");
        int stars = Integer.parseInt(request.getParameter("stars"));
        long eventId = Long.parseLong(request.getParameter("detailsEventId"));

        ratingService.rate(user, stars, eventId);

        return "redirect:/details";
    }
}
