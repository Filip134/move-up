package com.moveUp.spring.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RatingId implements Serializable
{
    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne(targetEntity = User.class)
    protected User user;
    @JoinColumn(name = "event_id", nullable = false)
    @ManyToOne(targetEntity = Event.class)
    private Event event;

    public RatingId() { }

    public RatingId(User user, Event event)
    {
        this.user = user;
        this.event = event;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public Event getEvent()
    {
        return event;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof RatingId)) return false;
        RatingId that = (RatingId) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getEvent(), that.getEvent());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getUser(), getEvent());
    }
}