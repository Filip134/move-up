package com.moveUp.spring.controller;

import com.moveUp.spring.dto.CommentDto;
import com.moveUp.spring.model.User;
import com.moveUp.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController
{
    @Autowired
    CommentService commentService;

    @PostMapping("/addcomment")
    public String addComment(@ModelAttribute("commentDto") CommentDto commentDto, HttpSession session, Model model, HttpServletRequest request)
    {
        User user = (User)session.getAttribute("user");

        if(user == null)
            return "redirect:/";
        else
        {
            long eventId = Long.parseLong(request.getParameter("commentEventId"));
            commentDto.setEventId(eventId);
            commentDto.setUser(user);
            commentService.addComment(commentDto);
            return "redirect:/details";
        }
    }
}
