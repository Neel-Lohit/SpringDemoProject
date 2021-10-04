package com.example.springdemo.service;

import com.example.springdemo.dao.InvestorDao;
import com.example.springdemo.dao.UserDao;
import com.example.springdemo.entity.Investor;
import com.example.springdemo.entity.Role;
import com.example.springdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class SecurityServiceImpl implements SecurityService{


    @Autowired
    private UserDao userDao;

    @Autowired
    private InvestorDao investorDao;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userDao.findByUserName(userName);
        Investor investor = investorDao.findByUserName(userName);
        UserDetails userDetails;
        if (user != null) {
            userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),mapRolesToAuthorities(user.getRoles()));

        }
        else if (investor!=null)
        {
            userDetails = new org.springframework.security.core.userdetails.User(investor.getUserName(), investor.getPassword(),mapRolesToAuthorities(investor.getRoles()));
        }
        else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return userDetails;
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
