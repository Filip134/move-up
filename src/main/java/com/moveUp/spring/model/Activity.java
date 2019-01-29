package com.moveUp.spring.model;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import javax.persistence.*;

@Entity
public class Activity extends AbstractModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //zmienna określająca czy aktywność jest sportem zespołowym czy nie
    private boolean team;
    private String name;

    public Activity(String name, boolean team)
    {
        this.name = name;
        this.team = team;
    }

    public Activity(){}

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
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
