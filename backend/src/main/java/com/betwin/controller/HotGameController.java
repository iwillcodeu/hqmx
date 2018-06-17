package com.betwin.controller;

import com.betwin.entity.HotGame;
import com.betwin.service.HotGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/hotgame")
public class HotGameController {

    @Autowired
    private HotGameService hotGameService;

    @RequestMapping(value = "/top", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public List<HotGame> findTopHotGames() {
        return hotGameService.findTopHotGames();
    }

}
