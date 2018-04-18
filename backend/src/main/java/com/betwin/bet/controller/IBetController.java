package com.betwin.bet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.betwin.bet.dto.BetDto;
import com.betwin.bet.model.BetEntity;

public interface IBetController {

    ResponseEntity<BetDto> getBetById(String id);

    ResponseEntity<List<BetDto>> getBeteByAccount(String account);

    ResponseEntity<List<BetEntity>> findBetByCondition(String condition);

    ResponseEntity<List<BetEntity>> findBetByDate(String condition);

    ResponseEntity<BetEntity> createBet(BetDto betDto, BindingResult bindingResult);

    ResponseEntity<BetEntity> updateBet(BetDto betDto, BindingResult bindingResult);
}
