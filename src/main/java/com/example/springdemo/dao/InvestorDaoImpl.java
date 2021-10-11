package com.example.springdemo.dao;

import com.example.springdemo.entity.Investor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class InvestorDaoImpl implements InvestorDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Investor findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<Investor> theQuery = currentSession.createQuery("from Investor where userName=:uName", Investor.class);
		theQuery.setParameter("uName", theUserName);
		Investor theInvestor = null;
		try {
			theInvestor = theQuery.getSingleResult();
		} catch (Exception e) {
			theInvestor = null;
		}

		return theInvestor;
	}



}
