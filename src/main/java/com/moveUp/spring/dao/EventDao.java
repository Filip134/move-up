package com.moveUp.spring.dao;

import com.moveUp.spring.model.*;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Repository
@Transactional
public class EventDao extends AbstractDao
{
    @Autowired
    UserDao userDao;

    public boolean addEvent(Event event)
    {
        getSession().saveOrUpdate(event);
        return  true;
    }

    public boolean addUserToEvent(User user, Event event)
    {
        if(event.getJoinNo()+1 > event.getMaxJoin())
            return false;
        if(event.getUsers().contains(user))
            return false;

        event.incrementJoinNo();
        event.getUsers().add(user);
        user.getEvents().add(event);

        getSession().saveOrUpdate(user);
        getSession().saveOrUpdate(event);

        return true;
    }

    public boolean removeUserFromEvent(User user, Event event)
    {
        if(!event.getUsers().contains(user))
            return false;

        event.getUsers().remove(user);
        user.getEvents().remove(event);

        event.decrementJoinNo();

        getSession().saveOrUpdate(user);
        getSession().saveOrUpdate(event);

        return true;
    }

    public boolean deleteById(long id)
    {
        Event event = (Event) getSession().load(Event.class, id);
        getSession().delete(event);

        return true;
    }

    public List<Event> getEvents()
    {
        Query q = getSession().createQuery("from Event");
        return q.list();
//        return getSession().createCriteria(Event.class).list();
    }

    public Event getEventById(long id)
    {
        return (Event) getSession().get(Event.class, id);
    }

    public List<Event> getEventsByName(String name)
    {
        Query q = getSession().createQuery("from Event where name=:name");
        q.setParameter("name", name);

        return q.list();
    }

    public List<Event> getEventsByDescription(String description)
    {
        Query q = getSession().createQuery("from Event where description=:description");
        q.setParameter("description", description);

        return q.list();
    }

    public List<Event> getEventsByPlace(String place)
    {
        Query q = getSession().createQuery("from Event where place=:place");
        q.setParameter("place", place);

        return q.list();
    }

    public List<Event> getEventsByJoinNo(int joinNo)
    {
        Query q = getSession().createQuery("from Event where joinNo=:joinNo");
        q.setParameter("joinNo", joinNo);

        return q.list();
    }

    public List<Event> getEventsByMaxJoin(int maxJoin)
    {
        Query q = getSession().createQuery("from Event where maxJoin=:maxJoin");
        q.setParameter("maxJoin", maxJoin);

        return q.list();
    }

    public List<Event> getEventsByAdvancement(Advancement advancement)
    {
        Query q = getSession().createQuery("from Event where advancement=:advancement");
        q.setParameter("advancement", advancement);

        return q.list();
    }

    public List<Event> getEventsByCreator(User creator)
    {
        Query q = getSession().createQuery("from Event where creator=:creator");
        q.setParameter("creator", creator);

        return q.list();
    }

    public List<Event> getEventsByCreatorLogin(String login)
    {
        Query q = getSession().createQuery("from Event where creator_login=:creatorLogin");
        q.setParameter("creatorLogin", login);

        return q.list();
    }

    public List<Event> getEventsByDate(java.util.Date date)
    {
        Query q = getSession().createQuery("from Event where date=:date");
        q.setParameter("date", date);

        return q.list();
    }

    public List<Event> getEventsByDate(int year, int month, int day)
    {
        java.util.Date date;
        long diff;
        Calendar c = Calendar.getInstance();
        c.set(year, month-1, day, 0, 0, 0);
        date = c.getTime();

        List<Event> events = getEvents();
        List<Event> rteturedEvents = new ArrayList<Event>();

        for(Event e: events)
        {
            diff = e.getDate().getTime() - date.getTime();
            if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) == 0)
                rteturedEvents.add(e);
        }

        return rteturedEvents;
    }

    public List<Event> getEventsByActivity(Activity activity)
    {
        Query q = getSession().createQuery("from Event where activity=:activity");
        q.setParameter("activity", activity);
        return q.list();
    }

    public List<Event> getJoinableEvents(User user)
    {
        List<Event> events = user.getEvents();
        Query q;

        if(events.isEmpty())
            q = getSession().createQuery("from Event");
        else
        {
            q = getSession().createQuery("from Event as e where e not in (:events)");
            q.setParameterList("events", events);
        }

        return q.list();
    }

    public void update(Event event)
    {
        getSession().update(event);
    }

    public void saveOrUpdate(Event event)
    {
        getSession().saveOrUpdate(event);
    }
}
