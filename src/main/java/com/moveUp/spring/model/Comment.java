package com.moveUp.spring.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Comment extends AbstractModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @ManyToOne(cascade = CascadeType.ALL)
    private Event event;
    @ManyToOne
    private User user;
    private Date date;

    public Comment(String content, Event event, User user, Date date)
    {
        this.content = content;
        this.event = event;
        this.user = user;
        this.date = date;
    }

    public Comment(){}

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public Event getEvent()
    {
        return event;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }
}
