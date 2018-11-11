package com.moveUp.spring.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Activity extends AbstractModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //zmienna określająca czy aktywność jest sportem zespołowym czy nie
    private boolean team;
    private String name;

    public Activity(boolean team, String name)
    {
        this.team = team;
        this.name = name;
    }

    public Activity(){}

    @Override
    public long getId()
    {
        return id;
    }

    public boolean isTeam()
    {
        return team;
    }

    public void setTeam(boolean team)
    {
        this.team = team;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
