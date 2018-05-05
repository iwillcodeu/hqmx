package com.betwin.game.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.betwin.common.Condition;
import com.betwin.game.model.GameEntity;

@Repository
public class GameDaoImpl implements IGameDao {

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

    @Override
    public List<GameEntity> findGameByName(String gameName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(gameName));
        List<GameEntity> gameList = mongoTemplate.find(query, GameEntity.class);
        return gameList;
    }

    @Override
    public List<GameEntity> findGameByCondition(List<Condition> conditions, int begin, int end, String orderField,
            Direction direction) {
        Query query = new Query();
        for (Condition condition : conditions) {
            query.addCriteria(
                    Criteria.where(condition.getField()).regex(".*?\\" + condition.getValue().toString() + ".*"));
        }

        List<GameEntity> gameList = mongoTemplate
                .find(query.limit(end - begin).skip(begin).with(new Sort(direction, orderField)), GameEntity.class);
        return gameList;
    }

    @Override
    public GameEntity update(GameEntity game) {
        mongoTemplate.save(game);
        return game;
    }

    @Override
    public List<GameEntity> findGameByDate(Date beginDate, Date endDate, String field, int begin, int end,
            Direction direction) {
        Query query = new Query();
        query.addCriteria(Criteria.where(field).gte(beginDate).lte(endDate)).limit(end - begin).skip(begin)
                .with(new Sort(direction, field));
        List<GameEntity> gameList = mongoTemplate.find(query, GameEntity.class);
        return gameList;
    }

}
