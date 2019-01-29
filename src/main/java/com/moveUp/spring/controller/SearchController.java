package com.moveUp.spring.controller;

import com.moveUp.spring.model.Event;
import com.moveUp.spring.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController
{
    @Autowired
    EventService eventService;

    @PostMapping("/search")
    public String Search(HttpServletRequest request, Model model)
    {
        String text = request.getParameter("query");
        List<Event> events = eventService.getEventsByText(text);

        model.addAttribute("events", events);

        return "search";
    }
}
