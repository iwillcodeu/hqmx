package com.betwin.game.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

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

import com.betwin.common.Condition;
import com.betwin.game.dto.GameDto;
import com.betwin.game.model.GameEntity;
import com.betwin.game.service.IGameService;

@RestController
@RequestMapping(path = "/game")
public class GameControllerImpl implements IGameController {

    @Autowired
    private IGameService gameService;

    private static Logger log = LoggerFactory.getLogger(GameControllerImpl.class);

    @Override
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<GameEntity> getGameById(@PathVariable String id) {
        if (null == id) {
            log.error("input game id is null");
            return new ResponseEntity<GameEntity>(HttpStatus.BAD_REQUEST);
        }
        GameEntity game = gameService.findGameById(id);
        if (null == game) {
            log.warn("can not find the game by id %s", id);
            return new ResponseEntity<GameEntity>(game, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<GameEntity>(game, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public ResponseEntity<List<GameEntity>> getGameByName(@PathVariable String name) {
        if (null == name) {
            log.error("input game name is null");
            return new ResponseEntity<List<GameEntity>>(HttpStatus.BAD_REQUEST);
        }
        List<GameEntity> gameList = gameService.findGameByName(name);
        if (null == gameList) {
            log.warn("can not find the game by name %s", name);
            return new ResponseEntity<List<GameEntity>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<GameEntity>>(gameList, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/condition/{condition}", method = RequestMethod.GET)
    public ResponseEntity<List<GameEntity>> findGameByCondition(@PathVariable String condition) {
        if (null == condition) {
            log.error("input condition is null");
            return new ResponseEntity<List<GameEntity>>(HttpStatus.BAD_REQUEST);
        }
        StringTokenizer st = new StringTokenizer(condition, "&");
        List<Condition> conditionList = new ArrayList<>();
        int begin = 0;
        int end = 0;
        String orderField = "";
        String direction = "";
        while (st.hasMoreTokens()) {
            String childStr = st.nextToken();
            String[] strArray = childStr.split("=");
            if ("begin".equalsIgnoreCase(strArray[0])) {
                begin = Integer.parseInt(strArray[1]);
            } else if ("end".equalsIgnoreCase(strArray[0])) {
                end = Integer.parseInt(strArray[1]);
            } else if ("orderField".equalsIgnoreCase(strArray[0])) {
                orderField = strArray[1];
            } else if ("direction".equalsIgnoreCase(strArray[0])) {
                direction = strArray[1];
            } else {
                conditionList.add(new Condition(strArray[0], strArray[1]));
            }
        }

        List<GameEntity> gameList = gameService.findGameByCondition(conditionList, begin, end, orderField, direction);
        return new ResponseEntity<List<GameEntity>>(gameList, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/date/{condition}", method = RequestMethod.GET)
    public ResponseEntity<List<GameEntity>> findGameByDate(@PathVariable String condition) {
        if (null == condition) {
            log.error("input condition is null");
            return new ResponseEntity<List<GameEntity>>(HttpStatus.BAD_REQUEST);
        }
        StringTokenizer st = new StringTokenizer(condition, "&");
        Date beginDate = null;
        Date endDate = null;
        int begin = 0;
        int end = 0;
        String field = "";
        String direction = "";
        while (st.hasMoreTokens()) {
            String childStr = st.nextToken();
            String[] strArray = childStr.split("=");
            if ("begin".equalsIgnoreCase(strArray[0])) {
                begin = Integer.parseInt(strArray[1]);
            } else if ("end".equalsIgnoreCase(strArray[0])) {
                end = Integer.parseInt(strArray[1]);
            } else if ("orderField".equalsIgnoreCase(strArray[0])) {
                field = strArray[1];
            } else if ("direction".equalsIgnoreCase(strArray[0])) {
                direction = strArray[1];
            } else {
            }
        }

        List<GameEntity> gameList = gameService.findGameByDate(beginDate, endDate, field, begin, end, direction);
        return new ResponseEntity<List<GameEntity>>(gameList, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<GameEntity> createGame(@RequestBody GameDto gameDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("errors in request body");
            return new ResponseEntity<GameEntity>(HttpStatus.BAD_REQUEST);
        }
        GameEntity game = gameService.saveGame(gameDto);
        return new ResponseEntity<GameEntity>(game, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity<GameEntity> updateGame(@RequestBody GameDto gameDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("errors in request body");
            return new ResponseEntity<GameEntity>(HttpStatus.BAD_REQUEST);
        }
        GameEntity game = gameService.update(gameDto);
        return new ResponseEntity<GameEntity>(game, HttpStatus.ACCEPTED);
    }

}
