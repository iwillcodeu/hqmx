package com.betwin.game.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.betwin.game.model.GameEntity;

@Repository
public class GameDao implements IGameDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public GameEntity findGameById(String id) {
        return mongoTemplate.findById(id, GameEntity.class);
    }

    @Override
    public GameEntity saveGame(GameEntity game) {
        mongoTemplate.save(game);
        return game;
    }

}
