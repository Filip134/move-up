package com.moveUp.spring.service;

import com.moveUp.spring.dao.ActivityDao;
import com.moveUp.spring.dao.EventDao;
import com.moveUp.spring.dao.UserDao;
import com.moveUp.spring.dto.EventDto;
import com.moveUp.spring.model.Event;
import com.moveUp.spring.model.Advancement;
import com.moveUp.spring.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventService
{
    @Autowired
    private EventDao eventDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private ActivityService activityService;

    public boolean addEvent(EventDto eventDto)
    {
        Event event = new Event();
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setCreator(eventDto.getCreator());

        if(eventDto.getActivityName() != null)
        {
            if(!eventDto.getActivityName().equals(""))
                event.setActivity(activityService.addActivity(eventDto.getActivityName(), eventDto.getTeam()));
            else
                event.setActivity(activityDao.getActivityById(eventDto.getActivityId()));
        }
        else
            event.setActivity(activityDao.getActivityById(eventDto.getActivityId()));

        event.setLatitude(eventDto.getLatitude());
        event.setLongitude(eventDto.getLongitude());
        event.setMaxJoin(eventDto.getMaxJoin());
        event.setDescription(eventDto.getDescription());
        event.setName(eventDto.getName());
        event.setPlaceName(eventDto.getPlaceName());

        if(eventDto.getAdvancement().equals("beginner"))
            event.setAdvancement(Advancement.BEGINNER);
        else if(eventDto.getAdvancement().equals("intermediate"))
            event.setAdvancement(Advancement.INTERMEDIATE);
        else if(eventDto.getAdvancement().equals("advanced"))
            event.setAdvancement(Advancement.ADVANCED);


        if(eventDao.addEvent(event))
            return true;
        else
            return false;
    }

    public List<Event>getEventsByCreator(User user)
    {
        return eventDao.getEventsByCreator(user);
    }

//    public List<Event> getCreatedEvents(String login)
//    {
//        return eventDao.getEventsByCreatorLogin(login);
//    }

    public List<Event> getAllEvents()
    {
        return eventDao.getEvents();
    }

    public void addUserToEvent(String login, long eventId)
    {
        eventDao.addUserToEvent(userDao.getUserByLogin(login), eventDao.getEventById(eventId));
    }

    public List<Event> getJoinableEvents(String login)
    {
        User user = userDao.getUserByLogin(login);
        return eventDao.getJoinableEvents(user);
    }

    public Event getEventById(long id)
    {
        return eventDao.getEventById(id);
    }

    public void removeUserFromEvent(User user, long eventId)
    {
        Event event = eventDao.getEventById(eventId);
        eventDao.removeUserFromEvent(user, event);
    }

    public List<Event> getJoinableEvents(User user)
    {
        return eventDao.getJoinableEvents(user);
    }

    public void deleteEventById(long id)
    {
        eventDao.deleteById(id);
    }

    public void addUserToEvent(User user, long eventId)
    {
        Event event = eventDao.getEventById(eventId);

        for(User u: event.getUsers())
        {
            if(u.getLogin().equals(user.getLogin()))
                return;
        }

        eventDao.addUserToEvent(user, event);
    }

    public List<Event> getEventsByText(String text)
    {
        return eventDao.getEventsByText(text);
    }
}
