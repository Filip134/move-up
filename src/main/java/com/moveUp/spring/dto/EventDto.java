package com.moveUp.spring.dto;

public class EventDto
{
    private String name;
    private String date;
    private String place;
    private String time;
    private int maxJoin;
    private long activityId;
    private String creatorLogin;
    private String description;
    private String advancement;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getPlace()
    {
        return place;
    }

    public void setPlace(String place)
    {
        this.place = place;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public int getMaxJoin()
    {
        return maxJoin;
    }

    public void setMaxJoin(int maxJoin)
    {
        this.maxJoin = maxJoin;
    }

    public long getActivityId()
    {
        return activityId;
    }

    public void setActivityId(long activityId)
    {
        this.activityId = activityId;
    }

    public String getCreatorLogin()
    {
        return creatorLogin;
    }

    public void setCreatorLogin(String creatorLogin)
    {
        this.creatorLogin = creatorLogin;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getAdvancement()
    {
        return advancement;
    }

    public void setAdvancement(String advancement)
    {
        this.advancement = advancement;
    }
}

