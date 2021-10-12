package com.example.springdemo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.example.springdemo.dao.UserRepository;
import com.example.springdemo.entity.Projects;
import com.example.springdemo.entity.Role;
import com.example.springdemo.entity.User;
import com.example.springdemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
 class UserServiceTests
{

    @Autowired
    UserService service;

    @MockBean
    UserRepository dao;

    @Test
    void testFindAllEmployees()
    {
        List<User> list = new ArrayList<User>();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        List<Projects> projects = new ArrayList<>();
        User usOne = new User(7,"sharan", "sharan123", "sharan", "sai", "sharan@gmail.com", "8074775461", "male", "b.tech", "zemoso", "Hyderabad", "telangana",roles,projects);
        list.add(usOne);


        when(dao.findAll()).thenReturn(list);

        //test
        List<User> empList = service.findAll();

        assertEquals(1, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    void testCreateOrSaveEmployee()
    {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        List<Projects> projects = new ArrayList<>();
        User user = new User(8,"saggam", "saggam123", "saggam", "pranav", "pranav@gmail.com", "8074775461", "male", "b.tech", "zemoso", "Hyderabad", "telangana",roles,projects);

        service.save(user);

        verify(dao, times(1)).save(user);
    }

    @Test
    void testFindEmployeeById()
    {

        int id = 8;

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_USER"));
        List<Projects> projects = new ArrayList<>();
        List<User> list = new ArrayList<User>();
        User user = new User(8,"saggam", "saggam123", "saggam", "pranav", "pranav@gmail.com", "8074775461", "male", "b.tech", "zemoso", "Hyderabad", "telangana",roles,projects);

        list.add(user);


        when(dao.findById(id)).thenReturn(java.util.Optional.of(user));

        //test
        User userTest = service.findById(id);

        assertEquals(user,userTest);
        verify(dao, times(1)).findById(id);
    }


}
