package com.moveUp.spring.controller;

import com.moveUp.spring.dto.CommentDto;
import com.moveUp.spring.dto.EventDto;
import com.moveUp.spring.dto.RatingDto;
import com.moveUp.spring.model.Event;
import com.moveUp.spring.model.User;
import com.moveUp.spring.service.ActivityService;
import com.moveUp.spring.service.EventService;
import com.moveUp.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class EventController
{
    @Autowired
    ActivityService activityService;
    @Autowired
    EventService eventService;
    @Autowired
    UserService userService;

    @GetMapping("/participate")
    public String paricipate(Model model, HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        model.addAttribute("participateEvents", user.getEvents());

        return "participate";
    }

    @GetMapping("/addevent")
    public String addEventGet(HttpSession session, Model model)
    {
        model.addAttribute("activities", activityService.getActivities());

        if(session.getAttribute("user") == null)
            return "redirect:/";
        else
        {
            EventDto eventDto = new EventDto();
            model.addAttribute("eventDto", eventDto);
            return "addevent";
        }
    }

    @PostMapping("/addevent")
    public String addEventPost(@ModelAttribute("eventDto") EventDto eventDto, HttpSession session)
    {
        if(session.getAttribute("user") == null)
            return "redirect:/";
        else
        {
            eventDto.setCreator((User) session.getAttribute("user"));
            if(eventDto.getDescription() == null)
                eventDto.setDescription("");
            eventService.addEvent(eventDto);
            return "redirect:/";
        }
    }

    @GetMapping("/created")
    public String createdGet(HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");

        model.addAttribute("createdEvents", eventService.getEventsByCreator(user));

        return "created";
    }

    @GetMapping("/search")
    public String searchGet(HttpSession session, Model model)
    {
        if(session.getAttribute("user") == null)
            model.addAttribute("allEvents", eventService.getAllEvents());
        else
            model.addAttribute("allEvents", eventService.getJoinableEvents((User) session.getAttribute("user")));

        return "events";
    }

    @PostMapping("deleteevent")
    public String deleteEventById(HttpServletRequest request, HttpSession session)
    {
        if(session.getAttribute("user") == null)
            return "redirect:/";

        long id = Long.parseLong(request.getParameter("deleteEvent"));

        eventService.deleteEventById(id);
        return "redirect:/created";
    }


    @GetMapping("/details")
    public String getDetails(HttpSession session, Model model)
    {
        if(session.getAttribute("eventDetailsId") == null)
            return "redirect:/";

        long eventDetailsId = (long) session.getAttribute("eventDetailsId");
        Event event = eventService.getEventById(eventDetailsId);

        model.addAttribute("eventDetails", event);
//        session.removeAttribute("eventDetailsId");
        CommentDto commentDto = new CommentDto();
        model.addAttribute("commentDto", commentDto);
        model.addAttribute("comments", event.getComments());

        return "details";
    }

    @PostMapping("/details")
    public String postDetails(HttpServletRequest request, HttpSession session, Model model)
    {
        User user = (User) session.getAttribute("user");

        if(user == null)
            return "redirect:/";

        long eventDetailsId = Long.parseLong(request.getParameter("eventDetailsId"));
        Event event = eventService.getEventById(eventDetailsId);

        model.addAttribute("eventDetails", event);
        session.setAttribute("eventDetailsId", eventDetailsId);

        CommentDto commentDto = new CommentDto();
        RatingDto ratingDto = new RatingDto();
        model.addAttribute("commentDto", commentDto);
        model.addAttribute("ratingDto", ratingDto);
        model.addAttribute("comments", event.getComments());

        return "details";
    }

    @PostMapping("/unenroll")
    public String removeUserFromEvent(HttpServletRequest request, HttpSession session)
    {
        User user = (User)session.getAttribute("user");

        if(user == null)
            return "redirect:/";

        long eventId = Long.parseLong(request.getParameter("eventDetailsId"));

        eventService.removeUserFromEvent(user, eventId);

        return "redirect:/participate";
    }
}
