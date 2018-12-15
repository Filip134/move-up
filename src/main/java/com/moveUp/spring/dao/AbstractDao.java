package com.moveUp.spring.dao;

import com.moveUp.spring.model.AbstractModel;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao
{
    @Autowired
    private SessionFactory factory;

    public Session getSession()
    {
        Session session;
        try { session = factory.getCurrentSession(); }
        catch (HibernateException e) { session = factory.openSession(); }

        return session;
    }

    public abstract boolean deleteById(long id);
}
