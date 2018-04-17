package com.betwin.game.service;

import java.util.Date;
import java.util.List;

import com.betwin.common.Condition;
import com.betwin.game.dto.GameDto;
import com.betwin.game.model.GameEntity;

public interface IGameService {

    GameEntity findGameById(String id);

    List<GameEntity> findGameByName(String gameName);

    List<GameEntity> findGameByCondition(List<Condition> conditions, int begin, int end, String orderField,
            String direction);

    List<GameEntity> findGameByDate(Date beginDate, Date endDate, String field, int begin, int end, String direction);

    GameEntity saveGame(GameDto gameDto);

    GameEntity update(GameDto gameDto);

}
