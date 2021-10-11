package com.example.springdemo.service;


import com.example.springdemo.dao.RoleDao;
import com.example.springdemo.dao.UserDao;
import com.example.springdemo.dao.UserRepository;
import com.example.springdemo.entity.User;
import com.example.springdemo.dto.CrmUser;
import com.example.springdemo.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository)
    {
        userRepository = theUserRepository;
    }


    @Override
    @Transactional
    public User findByUserName(String userName) {
        // check the database if the user already exists
        return userDao.findByUserName(userName);
    }

    @Override
    @Transactional
    public void save(CrmUser crmUser) {
        User user = new User();
        // assign user details to the user object
        user.setUserName(crmUser.getUserName());
        user.setPassword(passwordEncoder.encode(crmUser.getPassword()));
        user.setFirstName(crmUser.getFirstName());
        user.setLastName(crmUser.getLastName());
        user.setEmail(crmUser.getEmail());
        user.setPhoneNo(crmUser.getPhoneNo());
        user.setGender(crmUser.getGender());
        user.setQualification(crmUser.getQualification());
        user.setOrganisation(crmUser.getOrganisation());
        user.setCity(crmUser.getCity());
        user.setState(crmUser.getState());

        // give user default role of "employee"
        user.setRoles(Collections.singletonList(roleDao.findRoleByName("ROLE_USER")));

        // save user in the database
        userRepository.save(user);
    }



    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(int theId) {
        Optional<User> result = userRepository.findById(theId);

        User user;
        if (result.isPresent()){
            user = result.get();
        }
        else {
            throw new UserNotFoundException("Did not find User id - " + theId);
        }
        return user;
    }

    @Override
    public void save(User theUser) {
        userRepository.save(theUser);

    }

    @Override
    public void deleteById(int theId) {
        userRepository.deleteById(theId);
    }


}
