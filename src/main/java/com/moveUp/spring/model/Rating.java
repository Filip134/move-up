package com.moveUp.spring.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Rating implements Serializable
{
//    @Id
//    @JoinColumn(name = "event_id", nullable = false)
//    @ManyToOne(targetEntity = Event.class)
//    private Event event;
//
//    @Id
//    @JoinColumn(name = "user_id", nullable = false)
//    @ManyToOne(targetEntity = User.class)
//    protected User user;

    @EmbeddedId
    private RatingId ratingId;
    private int points;

    public Rating()
    {
    }

    public RatingId getRatingId()
    {
        return ratingId;
    }

    public void setRatingId(RatingId ratingId)
    {
        this.ratingId = ratingId;
    }

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }
}
