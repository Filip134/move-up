package com.moveUp.spring.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.mindrot.jbcrypt.BCrypt;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class User extends AbstractModel
{
    @Id
    private String login;
    private String password;
    //średnia na podstawie ocen organizowanych eventów
    private double average;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "creator", orphanRemoval=true)
    @Fetch(value = FetchMode.SUBSELECT)
    //stworzone eventy
    private List<Event> created = new ArrayList<Event>();
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    //eventy w których user brał udział
    private List<Event> events = new ArrayList<Event>();

    public User(String login, String password)
    {
        this.login = login;
        //hashowanie hasła
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.average = 0;
    }

    public User (){};

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }

    public double getAverage()
    {
        return average;
    }

    public void setAverage(double average)
    {
        this.average = average;
    }

    public List<Event> getCreated()
    {
        return created;
    }

    public void setCreated(List<Event> created)
    {
        this.created = created;
    }

    public void setPassword(String password)
    {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());;
    }

    public List<Event> getEvents()
    {
        return events;
    }

    public void setEvents(List<Event> events)
    {
        this.events = events;
    }

    public String getPassword() {return this.password;}


    public void removeEventById(long eventId)
    {
        for(Event e: events)
        {
            if(e.getId() == eventId)
            {
                events.remove(e);
                return;
            }
        }
    }
}
