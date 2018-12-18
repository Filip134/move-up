package com.moveUp.spring.service;

import com.moveUp.spring.dao.UserDao;
import com.moveUp.spring.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

        userDao.addUser(userDto);

        return true;
    }

    public boolean logIn(String login, String password)
    {
        if(userDao.credentials(login, password))
            return true;
        else
            return false;
    }
}
