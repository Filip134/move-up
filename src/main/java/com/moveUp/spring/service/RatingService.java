package com.moveUp.spring.service;

import com.moveUp.spring.dao.EventDao;
import com.moveUp.spring.dao.RatingDao;
import com.moveUp.spring.model.Event;
import com.moveUp.spring.model.Rating;
import com.moveUp.spring.model.RatingId;
import com.moveUp.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService
{
    @Autowired
    RatingDao ratingDao;
    @Autowired
    EventDao eventDao;

    public void rate(User user, int points, long eventId)
    {
        Event event = eventDao.getEventById(eventId);
        RatingId ratingId = new RatingId(user, event);
        Rating rating = new Rating();
        rating.setRatingId(ratingId);
        rating.setPoints(points);
        ratingDao.rate(rating);
        event.setAverage(eventDao.getEventAverage(event));
        eventDao.update(event);
    }
}
