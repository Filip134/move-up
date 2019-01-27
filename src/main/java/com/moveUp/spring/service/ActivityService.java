package com.moveUp.spring.service;

import com.moveUp.spring.dao.ActivityDao;
import com.moveUp.spring.model.Activity;
import com.moveUp.spring.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ActivityService
{
    @Autowired
    private ActivityDao activityDao;

    public Activity addActivity(String activityName, String team)
    {
        boolean t = true;

        if(team == null)
            t = false;

        Activity activity = new Activity(activityName, t);
        activityDao.addActivity(activity);

        return activity;
    }

    public List<Activity> getActivities()
    {
        return activityDao.getActivities();
    }
}
