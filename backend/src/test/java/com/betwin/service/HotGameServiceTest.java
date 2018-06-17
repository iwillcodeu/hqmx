package com.betwin.service;

import com.betwin.entity.HotGame;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotGameServiceTest {

    @Autowired
    private HotGameService hotGameService;


    @Test
    public void testFindTopHotGames() {
        List<HotGame> hotGames = hotGameService.findTopHotGames();
        assertEquals(3, hotGames.size());
    }


    @Test
    public void testCreateHotGame() {
        HotGame hotGame = new HotGame();
        hotGame.setGameDate(new Date());
        hotGame.setTeams("葡萄牙VS西班牙");
        hotGame.setMatch("世界杯");
        hotGameService.createHotGame(hotGame);
    }


} 
