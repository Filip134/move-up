package com.moveUp.spring.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String longitude;
    private String latitude;
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
    @Transient
    private java.util.Date time;
    @OneToMany(mappedBy = "event")
    private List<Coordinate> coordinates = new ArrayList<>();

    public Event(String name, String description, String longitude, String latitude, int maxJoin, Advancement advancement, User creator, java.util.Date date, Activity activity)
    {
        this.name = name;
        this.description = description;
        this.longitude = longitude;
        this.latitude = latitude;
        this.joinNo = 0;
        this.advancement = advancement;
        this.maxJoin = maxJoin;
        this.creator = creator;
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

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
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

    public void setDate(String date)
    {
        if(date == null)
            return;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try { this.date = format.parse(date); } catch (ParseException e)
        { e.printStackTrace(); }
    }

    public void setTime(String time)
    {
        if(time == null)
            return;

        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        try { this.time = format.parse(time); }
        catch (ParseException e) { e.printStackTrace(); }

        if(this.date != null)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(this.date);
            format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            int month = cal.get(Calendar.MONTH)+1;
            String dateString = "" + cal.get(Calendar.YEAR) + "-" + month + "-" + cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(this.time);
            dateString += " " + cal.get(Calendar.HOUR_OF_DAY) + ":" + cal.get(Calendar.MINUTE);
            try { this.date = format.parse(dateString); }
            catch (ParseException e) { e.printStackTrace(); }
        }
    }

    public java.util.Date getTime()
    {
        return this.time;
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

