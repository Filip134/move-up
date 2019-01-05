package com.moveUp.spring.model;


import javax.persistence.*;

@Entity
public class Coordinate
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Event event;
    private double latitude;
    private double longitude;

    public Coordinate(double latitude, double longitude, Event event)
    {
        this.latitude = latitude;
        this.longitude = longitude;
        this.event = event;
    }

    public Coordinate()
    {
    }

    public Event getEvent()
    {
        return event;
    }

    public void setEvent(Event event)
    {
        this.event = event;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }
}
