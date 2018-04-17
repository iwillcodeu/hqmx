package com.betwin.bet.service;

import java.util.List;

import com.betwin.bet.dto.BetDto;
import com.betwin.bet.model.BetEntity;

public interface IBetService {

    BetDto findBetById(String betId);

    List<BetDto> findBetByAccount(String account);

    BetEntity saveBet(BetDto betDto);

    BetEntity updatebet(BetDto betDto);
}
