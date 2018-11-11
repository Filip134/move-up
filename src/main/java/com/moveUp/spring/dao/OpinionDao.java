package com.moveUp.spring.dao;


import com.moveUp.spring.model.Event;
import com.moveUp.spring.model.Opinion;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class OpinionDao extends AbstractDao
{
    public void addOpinion(Opinion opinion)
    {
        opinion.getEvent().getOpinions().add(opinion);
        getSession().saveOrUpdate(opinion.getEvent());
        getSession().saveOrUpdate(opinion.getEvent());
        getSession().saveOrUpdate(opinion);

        double average = 0;

        Query q = getSession().createQuery("from Event as e inner join e.opinions as o where creator_id = " + opinion.getEvent().getCreator().getId());
        List<?> list = q.list();

        for(int i=0; i<list.size(); i++)
        {
            Object[] row = (Object[]) list.get(i);
            Event e = (Event) row[0];
            Opinion o = (Opinion) row[1];

            average += o.getStars();
        }

        average /= list.size();

        opinion.getEvent().getCreator().setAverage(average);
        getSession().update(opinion.getEvent().getCreator());
    }


    public void deleteById(long id)
    {
        Opinion opinion = (Opinion)getSession().load(Opinion.class, id);
        getSession().delete(opinion);
    }

    public List<Opinion> getOpinions()
    {
        return getSession().createCriteria(Opinion.class).list();
    }

    public Opinion getOpinionById(long id)
    {
        return (Opinion) getSession().get(Opinion.class, id);
    }

    public List<Opinion> getOpinionsByEvent(Event event)
    {
        Query q = getSession().createQuery("from Opinion where event:=event");
        q.setParameter("event", event);
        return q.list();
    }

    public List<Opinion> getOpinionsByEventId(long id)
    {
        Query q = getSession().createQuery("from Opinion where event_id:=id");
        q.setParameter("id", id);
        return q.list();
    }

    public List<Opinion> getOpinionsByContent(String content)
    {
        Query q = getSession().createQuery("from Opinion where content:=content");
        q.setParameter("content", content);
        return q.list();
    }

    public List<Opinion> getOpinionsByStars(int stars)
    {
        Query q = getSession().createQuery("from Opinion where stars:=stars");
        q.setParameter("stars", stars);
        return q.list();
    }

    public void update(Opinion opinion)
    {
        getSession().update(opinion);
    }

    public void saveOrUpdate(Opinion opinion)
    {
        getSession().saveOrUpdate(opinion);
    }
}
