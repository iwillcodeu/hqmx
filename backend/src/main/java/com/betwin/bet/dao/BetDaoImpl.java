package com.betwin.bet.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.betwin.bet.model.BetEntity;

@Repository
public class BetDaoImpl implements IBetDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BetEntity saveBet(BetEntity betEntity) {
        mongoTemplate.save(betEntity);
        return betEntity;
    }

    @Override
    public BetEntity findBetById(String betId) {
        return mongoTemplate.findById(betId, BetEntity.class);
    }

    @Override
    public List<BetEntity> findByAccount(String account) {
        Query query = new Query();
        query.addCriteria(Criteria.where("account").is(account));
        List<BetEntity> betList = mongoTemplate.find(query, BetEntity.class);
        return betList;
    }

    @Override
    public BetEntity updateBet(BetEntity betEntity) {
        mongoTemplate.save(betEntity);
        return betEntity;
    }

}
