package com.moveUp.spring.controller;

import com.moveUp.spring.dto.UserDto;
import com.moveUp.spring.model.User;
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
public class UserController
{
    @Autowired
    UserService userService;
    @Autowired
    EventService eventService;

    @GetMapping("/login")
    public String loginGet(HttpSession session)
    {
        return "login";
    }

    @PostMapping("/login")
    public String loginPost(HttpServletRequest request, HttpSession session, Model model)
    {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = userService.login(login, password);

        if(user != null)
        {
            model.addAttribute("user", user);
            session.setAttribute("user", user);
            session.removeAttribute("wrongCredentials");
            return "redirect:/";
        }

        else
        {
            session.setAttribute("wrongCredentials", true);
            return "redirect:/login";
        }
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
        if(session.getAttribute("user") != null)
            session.removeAttribute("user");

        return "redirect:/";
    }

    @PostMapping("/registration")
    public String registrationPost(@ModelAttribute("userDto") UserDto userDto, HttpSession session)
    {
        if(userService.registerNewUser(userDto))
        {
            session.removeAttribute("takenLogin");
            return "redirect:/";
        }
        else
        {
            session.setAttribute("takenLogin", true);
            return "redirect:/registration";
        }
    }

    @GetMapping("/changePassword")
    public String changePasswordGet(Model model)
    {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "changePassword";
    }


    @PostMapping("/changePassword")
    public String registrationPost(@ModelAttribute("userDto") UserDto userDto, HttpSession session, HttpServletRequest request)
    {
        User user = userService.login(userDto.getLogin(), userDto.getPassword());
        String newPassword = request.getParameter("newPassword");

        if(user != null)
        {
            userService.changePassword(user, newPassword);
            session.removeAttribute("takenLogin");
            userService.changePassword(user, newPassword);
            session.removeAttribute("wrongCredentials");
            return "redirect:/";
        }
        else
        {
            session.setAttribute("wrongCredentials", true);
            return "redirect:/changePassword";
        }
    }

    @PostMapping("/join")
    public String join(HttpSession session, HttpServletRequest request)
    {
        User user = (User) session.getAttribute("user");
        long eventId = Long.parseLong(request.getParameter("eventId"));
        eventService.addUserToEvent(user, eventId);

        return "redirect:/participate";
    }
}
