package com.moveUp.spring.service;

import com.moveUp.spring.dao.EventDao;
import com.moveUp.spring.dao.CommentDao;
import com.moveUp.spring.dto.CommentDto;
import com.moveUp.spring.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService
{
    @Autowired
    CommentDao commentDao;
    @Autowired
    EventDao eventDao;


    public void addComment(CommentDto commentDto)
    {
        Comment comment = new Comment();
        comment.setEvent(eventDao.getEventById(commentDto.getEventId()));
        comment.setContent(commentDto.getContent());
        comment.setUser(commentDto.getUser());
        comment.setDate(new Date());
        commentDao.addComment(comment);
    }
}
