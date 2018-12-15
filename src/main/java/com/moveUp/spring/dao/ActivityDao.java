package com.moveUp.spring.dao;

import com.moveUp.spring.model.Activity;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ActivityDao extends AbstractDao
{
    public boolean addActivity(Activity activity)
    {
        getSession().saveOrUpdate(activity);
        return true;
    }

    public boolean deleteById(long id)
    {
        Activity activity = (Activity) getSession().load(Activity.class, id);

        if(activity == null)
            return false;

        getSession().delete(activity);

        return true;
    }

    public List<Activity> getActivities()
    {
        return getSession().createCriteria(Activity.class).list();
    }

    public List<Activity> getActivitiesByName(String name)
    {
        Query q = getSession().createQuery("from Activity where name=:name");
        q.setParameter("name", name);

        return q.list();
    }

    public List<Activity> getActivitiesByTeam(boolean isTeam)
    {
        Query q = getSession().createQuery("from Activity where team=:isTeam");
        q.setParameter("isTeam", isTeam);

        return q.list();
    }

    public Activity getActivityById(long id)
    {
        return (Activity) getSession().get(Activity.class, id);
    }

}
