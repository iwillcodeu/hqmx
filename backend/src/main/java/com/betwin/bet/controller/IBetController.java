package com.betwin.bet.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.betwin.bet.dto.BetDto;
import com.betwin.bet.model.BetEntity;

public interface IBetController {

    ResponseEntity<BetDto> getBetById(String id);

    ResponseEntity<List<BetDto>> getBetByAccount(String account);

    ResponseEntity<List<BetEntity>> findBetByCondition(String condition);

    ResponseEntity<List<BetEntity>> findBetByDate(String condition);

    ResponseEntity<BetEntity> createBet(BetEntity betNew, BindingResult bindingResult);

    ResponseEntity<BetEntity> updateBet(BetEntity betUpdate, BindingResult bindingResult);
}
