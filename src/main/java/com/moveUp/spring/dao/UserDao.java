package com.moveUp.spring.dao;


import com.moveUp.spring.dto.UserDto;
import com.moveUp.spring.model.User;
import org.hibernate.Query;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserDao extends AbstractDao
{
    public boolean addUser(User user)
    {
        if(user.getLogin() == null || user.getPassword() == null)
            return  false;

        if(!isLoginFree(user.getLogin()))
            return false;

        getSession().saveOrUpdate(user);
        return true;
    }

    public void addUser(UserDto userDto)
    {
        User user = new User(userDto.getLogin(), userDto.getPassword());
        getSession().saveOrUpdate(user);
    }

    public boolean deleteById(long id)
    {
        User user = (User)getSession().load(User.class, id);

        if(user == null)
            return false;

        getSession().delete(user);
        return true;
    }

    public List<User> getUsers()
    {
        return getSession().createCriteria(User.class).list();
    }

    public User getUserById(long id)
    {
        return (User) getSession().get(User.class, id);
    }

    public boolean isLoginFree(String login)
    {
        Query q = getSession().createQuery("from User where login = '" + login + "'");

        if(q.uniqueResult() != null)
            return false;
        else
            return true;
    }

    public boolean credentials(String login, String password)
    {
        Query q = getSession().createQuery("from User where login = '" + login + "'");

        if(q.uniqueResult() == null)
            return false;
        else
        {
            User u = (User)q.uniqueResult();

            if(BCrypt.checkpw(password, u.getPassword()))
                return true;
            else
                return false;
        }
    }

    public User getUsersByLogin(String login)
    {
        Query q = getSession().createQuery("from User where login:=login");
        q.setParameter("login", login);
        return (User)q.uniqueResult();
    }


    public List<User> getUsersByAvg(double average)
    {
        Query q = getSession().createQuery("from User where average:=average");
        q.setParameter("average", average);
        return q.list();
    }


    //zwraca użytowników ze średnią powyzej określonej
    public List<User> getUsersByBiggerAvg(double average)
    {
        Query q = getSession().createQuery("from User where average > " + average);
        return q.list();
    }


    public List<User> getUsersBySmallerAvg(double average)
    {
        Query q = getSession().createQuery("from User where average < " + average);
        return q.list();
    }
}
