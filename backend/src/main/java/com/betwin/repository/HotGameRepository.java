package com.betwin.repository;

import com.betwin.entity.HotGame;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotGameRepository extends MongoRepository<HotGame, String> {

}
