package com.betwin.game.dao;

import com.betwin.game.model.GameEntity;

public interface IGameDao {
    
    GameEntity findGameById(String id);
    
    GameEntity saveGame(GameEntity game);

}
