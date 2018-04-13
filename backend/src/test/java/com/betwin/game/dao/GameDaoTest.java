package com.betwin.game.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.betwin.game.model.GameEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameDaoTest {

    @Autowired
    private IGameDao gameDao;

    @Test
    public void testFindGameById() {
        String id = "5acf6ca937f65e0363536a98";
        GameEntity game = gameDao.findGameById(id);
        assertNotNull(game);
    }

    @Test
    public void testSaveGame() {
        String gameName = "RomaVsChina";
        String progress = "ongoing";
        String result = "1:0";

        GameEntity game = new GameEntity();
        game.setGameName(gameName);
        game.setProgress(progress);
        game.setResult(result);
        game.setCreateTime(new Date(System.currentTimeMillis()));
        game.setStartTime(new Date(System.currentTimeMillis()));
        game.setEndTime(new Date(System.currentTimeMillis()));

        gameDao.saveGame(game);
    }

}
