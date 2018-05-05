package com.betwin.game.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.betwin.common.Condition;
import com.betwin.game.dao.IGameDao;
import com.betwin.game.dto.GameDto;
import com.betwin.game.model.GameEntity;

@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private IGameDao gameDao;

    private static Logger log = LoggerFactory.getLogger(GameServiceImpl.class);

    @Override
    public GameEntity findGameById(String id) {
        GameEntity game = gameDao.findGameById(id);
        if (null == game) {
            log.warn("can not find the game by id %s", id);
        }
        return game;
    }

    @Override
    public List<GameEntity> findGameByName(String gameName) {
        List<GameEntity> gameList = gameDao.findGameByName(gameName);
        if (null == gameList || gameList.isEmpty()) {
            log.warn("can not find the game by name %s", gameName);
        }
        return gameList;
    }

    @Override
    public List<GameEntity> findGameByCondition(List<Condition> conditions, int begin, int end, String orderField,
            String direction) {
        Direction dir = Direction.fromString(direction);
        List<GameEntity> gameList = gameDao.findGameByCondition(conditions, begin, end, orderField, dir);
        if (null == gameList || gameList.isEmpty()) {
            log.warn("can not find the games by conditions.");
        }
        return gameList;
    }

    @Override
    public List<GameEntity> findGameByDate(Date beginDate, Date endDate, String field, int begin, int end,
            String direction) {
        Direction dir = Direction.fromString(direction);
        List<GameEntity> gameList = gameDao.findGameByDate(beginDate, endDate, field, begin, end, dir);
        if (null == gameList || gameList.isEmpty()) {
            log.warn("can not find the games by conditions.");
        }
        return gameList;
    }

    @Override
    public GameEntity saveGame(GameDto gameDto) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameName(gameDto.getGameName());
        gameEntity.setCreateBy(gameDto.getCreateBy());
        gameEntity.setCreateTime(new Date());
        gameEntity.setStartTime(gameDto.getStartTime());
        gameEntity.setEndTime(gameDto.getEndTime());
        gameEntity.setContractAddress(gameDto.getContractAddress());
        gameEntity.getOptions().addAll(gameDto.getOptions());

        gameDao.saveGame(gameEntity);
        return gameEntity;
    }

    @Override
    public GameEntity update(GameDto gameDto) {
        GameEntity gameEntity = gameDao.findGameById(gameDto.getId());
        if (null == gameEntity) {
            log.warn("can not find the game");
        }

        gameEntity.setGameName(gameDto.getGameName());
        gameEntity.setCreateBy(gameDto.getCreateBy());
        gameEntity.setStartTime(gameDto.getStartTime());
        gameEntity.setEndTime(gameDto.getEndTime());
        gameEntity.getOptions().clear();
        gameEntity.getOptions().addAll(gameDto.getOptions());
        gameEntity.setProgress(gameDto.getProgress());
        gameEntity.setResult(gameDto.getResult());
        gameEntity.setContractAddress(gameDto.getContractAddress());

        gameDao.update(gameEntity);
        return gameEntity;
    }

}
