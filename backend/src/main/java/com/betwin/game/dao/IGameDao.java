package com.betwin.game.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import com.betwin.common.Condition;
import com.betwin.game.model.GameEntity;

public interface IGameDao {

    GameEntity findGameById(String id);

    List<GameEntity> findGameByName(String gameName);

    List<GameEntity> findGameByCondition(List<Condition> conditions, int begin, int end, String orderField,
            Direction direction);

    List<GameEntity> findGameByDate(Date beginDate, Date endDate, String field, int begin, int end,
            Direction direction);

    GameEntity saveGame(GameEntity game);

    GameEntity update(GameEntity game);
}
