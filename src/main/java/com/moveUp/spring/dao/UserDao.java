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
    public boolean registerNewUser(User user)
    {
        if(user.getLogin() == null || user.getPassword() == null)
            return  false;

        if(!isLoginFree(user.getLogin()))
            return false;

        getSession().saveOrUpdate(user);
        return true;
    }

    public void registerNewUser(UserDto userDto)
    {
        User user = new User(userDto.getLogin(), userDto.getPassword());
        getSession().save(user);
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
        Query q = getSession().createQuery("from User where login=:login");
        q.setParameter("login", login);

        if(q.uniqueResult() != null)
            return false;
        else
            return true;
    }

    public User logIn(String login, String password)
    {
        Query q = getSession().createQuery("from User where login = :login");
        q.setParameter("login", login);

        User user = (User) q.uniqueResult();

        if(user == null)
            return null;

        else
        {
            if(BCrypt.checkpw(password, user.getPassword()))
                return user;
            else
                return null;
        }
    }

    public User getUserByLogin(String login)
    {
        User user = getSession().get(User.class, login);
        return user;
    }

    public void updateUser(User user)
    {
        getSession().update(user);
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
