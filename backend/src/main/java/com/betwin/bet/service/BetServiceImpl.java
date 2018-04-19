package com.betwin.bet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betwin.bet.dao.IBetDao;
import com.betwin.bet.dto.BetDto;
import com.betwin.bet.model.BetEntity;
import com.betwin.game.dao.IGameDao;
import com.betwin.game.model.GameEntity;

@Service
public class BetServiceImpl implements IBetService {

    @Autowired
    private IBetDao betDao;

    @Autowired
    private IGameDao gameDao;

    private static Logger log = LoggerFactory.getLogger(BetServiceImpl.class);

    @Override
    public BetDto findBetById(String betId) {
        BetEntity bet = betDao.findBetById(betId);
        if (null == bet) {
            log.warn("can not find bet by id %s", betId);
            return null;
        }
        GameEntity game = gameDao.findGameById(bet.getGameId());

        BetDto betDto = new BetDto();
        betDto.setBetId(betId);
        betDto.setAccount(bet.getAccount());
        betDto.setOption(bet.getOption());
        betDto.setAmount(bet.getAmount());
        betDto.setBetTime(bet.getBetTime());
        betDto.setWin(bet.isWin());
        betDto.setGameId(game.getId());
        betDto.setGameName(game.getGameName());
        betDto.setGameProgress(game.getProgress());
        betDto.setGameResult(game.getResult());
        betDto.setGameCreator(game.getCreateBy());
        betDto.setGameCreateTime(game.getCreateTime());
        betDto.setGameStartTime(game.getStartTime());
        betDto.setGameEndTime(game.getEndTime());
        return betDto;
    }

    @Override
    public List<BetDto> findBetByAccount(String account) {
        List<BetDto> betDtoList = new ArrayList<>();
        List<BetEntity> betList = betDao.findByAccount(account);
        if (null == betList || betList.isEmpty()) {
            log.warn("can not find bet by account %s", account);
            return betDtoList;
        }
        for (BetEntity bet : betList) {
            betDtoList.add(this.findBetById(bet.getId()));
        }
        return betDtoList;
    }

    @Override
    public BetEntity saveBet(BetEntity bet) {
        bet.setBetTime(new Date());
        bet.setWin(false);

        betDao.saveBet(bet);
        return bet;
    }

    @Override
    public BetEntity updatebet(BetEntity betUpdate) {
        BetEntity bet = betDao.findBetById(betUpdate.getId());
        bet.setAmount(betUpdate.getAmount());
        bet.setOption(betUpdate.getOption());
        bet.setWin(betUpdate.isWin());

        betDao.updateBet(bet);
        return bet;
    }

}
