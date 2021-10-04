package com.example.springdemo.service;


import com.example.springdemo.dao.*;
import com.example.springdemo.entity.Investor;
import com.example.springdemo.entity.Role;
import com.example.springdemo.dto.CrmUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvestorServiceImpl implements InvestorService {

    private InvestorRepository investorRepository;

    @Autowired
    private InvestorDao investorDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public InvestorServiceImpl(InvestorRepository theInvestorRepository)
    {
        investorRepository = theInvestorRepository;
    }


    @Override
    @Transactional
    public Investor findByUserName(String userName) {
        // check the database if the user already exists
        return investorDao.findByUserName(userName);
    }

    @Override
    @Transactional
    public void save(CrmUser crmUser) {
        Investor investor = new Investor();
        // assign investor details to the investor object
        investor.setUserName(crmUser.getUserName());
        investor.setPassword(passwordEncoder.encode(crmUser.getPassword()));
        investor.setFirstName(crmUser.getFirstName());
        investor.setLastName(crmUser.getLastName());
        investor.setEmail(crmUser.getEmail());
        investor.setPhoneNo(crmUser.getPhoneNo());
        investor.setGender(crmUser.getGender());
        investor.setQualification(crmUser.getQualification());
        investor.setOrganisation(crmUser.getOrganisation());
        investor.setCity(crmUser.getCity());
        investor.setState(crmUser.getState());

        // give investor default role of "employee"
        investor.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_INVESTOR")));

        // save investor in the database
        investorDao.save(investor);
    }



    @Override
    public List<Investor> findAll() {
        return investorRepository.findAll();
    }

    @Override
    public Investor findById(int theId) {
        Optional<Investor> result = investorRepository.findById(theId);

        Investor investor = null;
        if (result.isPresent()){
            investor = result.get();
        }
        else {
            throw new RuntimeException("Did not find User id - " + theId);
        }
        return investor;
    }

    @Override
    public void save(Investor theInvestor) {
        investorRepository.save(theInvestor);

    }

    @Override
    public void deleteById(int theId) {
        investorRepository.deleteById(theId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Investor investor = investorDao.findByUserName(userName);
        if (investor == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(investor.getUserName(), investor.getPassword(),
                mapRolesToAuthorities(investor.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
