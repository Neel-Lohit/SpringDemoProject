package com.example.springdemo;

import com.example.springdemo.dao.InvestorRepository;
import com.example.springdemo.entity.*;
import com.example.springdemo.service.InvestorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
 class InvServiceTests
{

    @Autowired
    InvestorService service;

    @MockBean
    InvestorRepository dao;

    @Test
    void testFindAllEmployees()
    {
        List<Investor> list = new ArrayList<>();
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_INVESTOR"));
        List<InvestorProjects> projects = new ArrayList<>();
        Investor inOne = new Investor(5,"azim premji", "azim123", "azim", "premji", "azim@gmail.com", "8074775461", "male", "b.tech", "wipro", "Hyderabad", "telangana",roles,projects);
        list.add(inOne);


        when(dao.findAll()).thenReturn(list);

        //test
        List<Investor> empList = service.findAll();

        assertEquals(1, empList.size());
        verify(dao, times(1)).findAll();
    }

    @Test
    void testCreateOrSaveEmployee()
    {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_INVESTOR"));
        List<InvestorProjects> projects = new ArrayList<>();
        Investor investor = new Investor(8,"sudha", "sudha123", "sudha", "murthy", "sudha@gmail.com", "8074775461", "female", "b.tech", "infosys", "Hyderabad", "telangana",roles,projects);

        service.save(investor);

        verify(dao, times(1)).save(investor);
    }

    @Test
    void testFindEmployeeById()
    {

        int id = 8;

        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ROLE_INVESTOR"));
        List<InvestorProjects> projects = new ArrayList<>();
        Investor investor = new Investor(8,"sudha", "sudha123", "sudha", "murthy", "sudha@gmail.com", "8074775461", "female", "b.tech", "infosys", "Hyderabad", "telangana",roles,projects);




        when(dao.findById(id)).thenReturn(java.util.Optional.of(investor));

        //test
        Investor investorTest = service.findById(id);

        assertEquals(investor,investorTest);
        verify(dao, times(1)).findById(id);
    }


}
