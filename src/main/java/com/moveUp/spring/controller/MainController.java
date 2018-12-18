package com.moveUp.spring.controller;

import com.moveUp.spring.dto.EventDto;
import com.moveUp.spring.dto.UserDto;
import com.moveUp.spring.service.ActivityService;
import com.moveUp.spring.service.EventService;
import com.moveUp.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class MainController
{
    @Autowired
    private UserService userService;
    @Autowired
    private EventService eventService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("/")
    public String index(HttpSession session)
    {
        return "index";
    }

    @GetMapping("/index")
    public String indexPath(HttpSession session)
    {
        return "index";
    }

    @GetMapping("/login")
    public String loginGet()
    {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, HttpSession session)
    {
        if(userService.logIn(request.getParameter("login"), request.getParameter("password")))
        {
            session.setAttribute("login", request.getParameter("login"));
            return "redirect:/";
        }

        else
            return "failure";
    }

    @GetMapping("/registration")
    public String registrationGet(Model model)
    {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session)
    {
        if(session.getAttribute("login") != null)
            session.removeAttribute("login");

        return "redirect:/";
    }

    @GetMapping("/addevent")
    public String addEventGet(HttpSession session, Model model)
    {
        model.addAttribute("activities", activityService.getActivities());

        if(session.getAttribute("login") == null)
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
        if(session.getAttribute("login") == null)
            return "redirect:/";
        else
        {
            String login = (String) session.getAttribute("login");
            eventDto.setCreatorLogin(login);
            eventService.addEvent(eventDto);
            return "redirect:/";
        }
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("userDto") UserDto userDto)
    {
        if(userService.registerNewUser(userDto))
        {
            return "redirect:/";
        }
        else
        {
            return "failure";
        }
    }

    @GetMapping("/created")
    public String createdGet(HttpSession session, Model model)
    {
        model.addAttribute("createdEvents", eventService.getCreatedEvents((String) session.getAttribute("login")));

        return "created";
    }

    @PostMapping("deleteevent")
    public String deleteEventById(HttpServletRequest request, HttpSession session)
    {
        if(session.getAttribute("login") == null)
            return "redirect:/";

        long id = Long.parseLong(request.getParameter("deleteEvent"));

        eventService.deleteEventById(id);
        return "redirect:/";
    }
}
