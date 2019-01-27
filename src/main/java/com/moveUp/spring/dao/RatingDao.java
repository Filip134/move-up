package com.moveUp.spring.dao;

import com.moveUp.spring.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RatingDao extends ActivityDao
{
    @Autowired
    EventDao eventDao;

    public void rate(Rating rating)
    {
        getSession().saveOrUpdate(rating);
    }
}
