package com.betwin.bet.dao;

import java.util.List;

import com.betwin.bet.model.BetEntity;

public interface IBetDao {

    BetEntity findBetById(String betId);

    List<BetEntity> findByAccount(String account);

    BetEntity saveBet(BetEntity betEntity);

    BetEntity updateBet(BetEntity betEntity);
}
