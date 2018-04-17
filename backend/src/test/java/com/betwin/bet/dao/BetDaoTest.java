package com.betwin.bet.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.betwin.bet.model.BetEntity;

public class BetDaoTest {

    @Autowired
    private IBetDao betDao;
    
    @Test
    public void testSaveBet() {
        BetEntity betEntity = new BetEntity();
    }

    @Test
    public void testFindBetById() {
        fail("Not yet implemented");
    }

}
