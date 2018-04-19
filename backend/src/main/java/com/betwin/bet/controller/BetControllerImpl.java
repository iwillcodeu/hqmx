package com.betwin.bet.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.betwin.bet.dto.BetDto;
import com.betwin.bet.model.BetEntity;
import com.betwin.bet.service.IBetService;

@RestController
@RequestMapping(path = "/bet")
public class BetControllerImpl implements IBetController {

    @Autowired
    private IBetService betService;

    private static Logger log = LoggerFactory.getLogger(BetControllerImpl.class);

    @Override
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<BetDto> getBetById(@PathVariable String id) {
        if (null == id) {
            log.error("input bet id is null");
            return new ResponseEntity<BetDto>(HttpStatus.BAD_REQUEST);
        }
        BetDto betDto = betService.findBetById(id);
        if (null == betDto) {
            log.warn("can not find the bet by id %s", id);
            return new ResponseEntity<BetDto>(betDto, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<BetDto>(betDto, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/account/{account}", method = RequestMethod.GET)
    public ResponseEntity<List<BetDto>> getBetByAccount(@PathVariable String account) {
        if (null == account) {
            log.error("input bet account is null");
            return new ResponseEntity<List<BetDto>>(HttpStatus.BAD_REQUEST);
        }
        List<BetDto> betDtoList = betService.findBetByAccount(account);
        if (null == betDtoList || betDtoList.isEmpty()) {
            log.warn("can not find the bet by account %s", account);
            return new ResponseEntity<List<BetDto>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<BetDto>>(betDtoList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<BetEntity>> findBetByCondition(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ResponseEntity<List<BetEntity>> findBetByDate(String condition) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BetEntity> createBet(@RequestBody BetEntity betNew, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("errors in request body");
            return new ResponseEntity<BetEntity>(HttpStatus.BAD_REQUEST);

        }
        BetEntity bet = betService.saveBet(betNew);
        return new ResponseEntity<BetEntity>(bet, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<BetEntity> updateBet(@RequestBody BetEntity betUpdate, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("errors in request body");
            return new ResponseEntity<BetEntity>(HttpStatus.BAD_REQUEST);

        }
        BetEntity bet = betService.updatebet(betUpdate);
        return new ResponseEntity<BetEntity>(bet, HttpStatus.ACCEPTED);
    }

}
