package com.moveUp.spring.service;

import com.moveUp.spring.dao.ActivityDao;
import com.moveUp.spring.dao.EventDao;
import com.moveUp.spring.dao.UserDao;
import com.moveUp.spring.dto.EventDto;
import com.moveUp.spring.model.Event;
import com.moveUp.spring.model.Advancement;
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

    public boolean addEvent(EventDto eventDto)
    {
        Event event = new Event();
        event.setDate(eventDto.getDate());
        event.setTime(eventDto.getTime());
        event.setCreator(userDao.getUserByLogin(eventDto.getCreatorLogin()));
        event.setActivity(activityDao.getActivityById(eventDto.getActivityId()));
        event.setPlace(eventDto.getPlace());
        event.setMaxJoin(eventDto.getMaxJoin());
        event.setDescription(eventDto.getDescription());
        event.setName(eventDto.getName());

        if(eventDto.getAdvancement().equals("begginer"))
            event.setAdvancement(Advancement.BEGGINER);
        else if(eventDto.getAdvancement().equals("intermediate"))
            event.setAdvancement(Advancement.INTERMEDIATE);
        else if(eventDto.getAdvancement().equals("advanced"))
            event.setAdvancement(Advancement.ADVANCED);


        if(eventDao.addEvent(event))
            return true;
        else
            return false;
    }

    public List<Event> getCreatedEvents(String login)
    {
        return eventDao.getEventsByCreatorLogin(login);
    }

    public void deleteEventById(long id)
    {
        eventDao.deleteById(id);
    }
}
