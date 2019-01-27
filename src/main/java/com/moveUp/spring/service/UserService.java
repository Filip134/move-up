package com.moveUp.spring.service;

import com.moveUp.spring.dao.UserDao;
import com.moveUp.spring.dto.UserDto;
import com.moveUp.spring.model.Event;
import com.moveUp.spring.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;


@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    public boolean registerNewUser(UserDto userDto)
    {
        if(userDto.getLogin() == null || userDto.getPassword() == null)
            return false;

        if(!userDao.isLoginFree(userDto.getLogin()))
            return false;

        if(!(userDto.getPassword().equals(userDto.getConfirmPassword())))
            return false;

        userDao.registerNewUser(userDto);

        return true;
    }

    public User login(String login, String password)
    {
        return userDao.logIn(login, password);
    }

    public User getUserByLogin(String login)
    {
        return userDao.getUserByLogin(login);
    }

    public Event getEventById(User user, long id)
    {
        for(Event event: user.getEvents())
        {
            if(event.getId() == id)
                return event;
        }

        return null;
    }

    public void changePassword(User user, String newPassword)
    {
        user.setPassword(newPassword);
        userDao.updateUser(user);
    }
}
