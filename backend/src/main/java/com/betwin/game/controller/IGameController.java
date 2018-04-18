package com.betwin.game.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import com.betwin.game.dto.GameDto;
import com.betwin.game.model.GameEntity;

public interface IGameController {

    ResponseEntity<GameEntity> getGameById(String id);

    ResponseEntity<List<GameEntity>> getGameByName(String name);

    ResponseEntity<List<GameEntity>> findGameByCondition(String condition);

    ResponseEntity<List<GameEntity>> findGameByDate(String condition);

    ResponseEntity<GameEntity> createGame(GameDto gameDto, BindingResult bindingResult);

    ResponseEntity<GameEntity> updateGame(GameDto gameDto, BindingResult bindingResult);

}
