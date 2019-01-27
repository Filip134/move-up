package com.moveUp.spring.dao;


import com.moveUp.spring.model.Comment;
import com.moveUp.spring.model.Event;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class CommentDao extends AbstractDao
{
    public boolean addComment(Comment comment)
    {
        comment.getEvent().getComments().add(comment);
        getSession().saveOrUpdate(comment.getEvent());
        getSession().saveOrUpdate(comment);

        double average = 0;

//        Query q = getSession().createQuery("from Event as e inner join e.comments as o where creator_login = " + comment.getEvent().getCreator().getLogin());
//        List<?> list = q.list();
//
//        for(int i=0; i<list.size(); i++)
//        {
//            Object[] row = (Object[]) list.get(i);
//            Event e = (Event) row[0];
//            Comment o = (Comment) row[1];
//
//            average += o.getStars();
//        }
//
//        average /= list.size();
//
//        comment.getEvent().getCreator().setAverage(average);
//        getSession().update(comment.getEvent().getCreator());

        return true;
    }

    public void addCommentToEvent(Comment comment, Event event)
    {
        event.getComments().add(comment);
        comment.setEvent(event);
        getSession().saveOrUpdate(comment);
        getSession().saveOrUpdate(event);

//        double average = 0;
//
//        Query q = getSession().createQuery("from Event as e inner join e.comments as o where creator_login = " + comment.getEvent().getCreator().getLogin());
//        List<?> list = q.list();
//
//        for(int i=0; i<list.size(); i++)
//        {
//            Object[] row = (Object[]) list.get(i);
//            Event e = (Event) row[0];
//            Comment o = (Comment) row[1];
//
//            average += o.getStars();
//        }
//
//        average /= list.size();
//
//        comment.getEvent().getCreator().setAverage(average);
        getSession().update(comment.getEvent().getCreator());
    }

    public boolean deleteById(long id)
    {
        Comment comment = (Comment)getSession().load(Comment.class, id);

        if(comment == null)
            return false;

        getSession().delete(comment);

        return true;
    }

    public List<Comment> getComments()
    {
        return getSession().createCriteria(Comment.class).list();
    }

    public Comment getCommentById(long id)
    {
        return (Comment) getSession().get(Comment.class, id);
    }

    public List<Comment> getCommentByEvent(Event event)
    {
        Query q = getSession().createQuery("from Comment where event:=event");
        q.setParameter("event", event);
        return q.list();
    }

    public List<Comment> getCommentsByEventId(long id)
    {
        Query q = getSession().createQuery("from Comment where event_id:=id");
        q.setParameter("id", id);
        return q.list();
    }

    public List<Comment> getCommentByContent(String content)
    {
        Query q = getSession().createQuery("from Comment where content:=content");
        q.setParameter("content", content);
        return q.list();
    }


    public void update(Comment comment)
    {
        getSession().update(comment);
    }

    public void saveOrUpdate(Comment comment)
    {
        getSession().saveOrUpdate(comment);
    }
}
