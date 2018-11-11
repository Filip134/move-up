package com.moveUp.spring.model;

import javax.persistence.*;

@Entity
public class Opinion extends AbstractModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    @ManyToOne
    private Event event;
    private int stars;

    public Opinion(String content, Event event, int stars)
    {
        this.content = content;
        this.event = event;
        this.stars = stars;
    }

    public Opinion(){}

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

    public int getStars()
    {
        return stars;
    }

    public void setStars(int stars)
    {
        this.stars = stars;
    }

    public long getId()
    {
        return id;
    }

}
