package com.moveUp.spring.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Entity
public class Event extends AbstractModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String place;
    //liczba zapisanych
    private int joinNo;
    //maksymalna liczba biorących udział
    private int maxJoin;
    private Advancement advancement;
    @ManyToOne
    private User creator;
    @ManyToMany(fetch = FetchType.EAGER)
    //użytkownicy zapisani na event
    private List<User> users = new ArrayList<User>();
    private java.util.Date date = null;
    @ManyToOne
    private Activity activity;
    private String description;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event", orphanRemoval=true)
    @Fetch(value = FetchMode.SUBSELECT)
    //opinie do eventu
    private List<Opinion> opinions = new ArrayList<Opinion>();

    public Event(String name, String description, String place, int maxJoin, Advancement advancement, User creator, Date date, Activity activity)
    {
        this.name = name;
        this.description = description;
        this.place = place;
        this.joinNo = 0;
        this.advancement = advancement;
        this.maxJoin = maxJoin;
        this.creator = creator;
        this.date = date;
        this.activity = activity;
    }

    //ustawianie daty na podstawie 5 zmiennych int
    public Event(String name, String description, String place, int maxJoin, Advancement advancement, int year, int month, int day, int hour, int minutes, User creator, Activity activity)
    {
        this.name = name;
        this.description = description;
        this.place = place;
        this.joinNo = 0;
        this.maxJoin = maxJoin;
        this.advancement = advancement;
        this.creator = creator;
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day, hour, minutes, 0);
        this.date = c.getTime();
        this.date = date;
        this.activity = activity;
    }

    public Event(){}

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public int getJoinNo()
    {
        return joinNo;
    }

    public void setJoinNo(int joinNo)
    {
        this.joinNo = joinNo;
    }

    public Advancement getAdvancement()
    {
        return advancement;
    }

    public void setAdvancement(Advancement advancement)
    {
        this.advancement = advancement;
    }

    public User getCreator()
    {
        return creator;
    }

    public void setCreator(User creator)
    {
        this.creator = creator;
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void setUsers(List<User> users)
    {
        this.users = users;
    }

    public java.util.Date getDate()
    {
        return date;
    }

    public void setDate(java.util.Date date)
    {
        this.date = date;
    }

    public Activity getActivity()
    {
        return activity;
    }

    public void setActivity(Activity activity)
    {
        this.activity = activity;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public List<Opinion> getOpinions()
    {
        return opinions;
    }

    public void setOpinions(List<Opinion> opinions)
    {
        this.opinions = opinions;
    }

    public int getMaxJoin()
    {
        return maxJoin;
    }

    public void setMaxJoin(int maxJoin)
    {
        this.maxJoin = maxJoin;
    }

    public void  setDate(int year, int month, int day, int hour, int minutes)
    {
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day, hour, minutes, 0);
        this.date = c.getTime();
    }

    public  void incrementJoinNo()
    {
        this.joinNo++;
    }

    public void decrementJoinNo()
    {
        if(this.joinNo > 0)
            this.joinNo--;
    }
}

