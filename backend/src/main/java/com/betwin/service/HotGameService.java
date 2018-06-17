package com.betwin.service;

import com.betwin.common.BetWinConstants;
import com.betwin.entity.HotGame;
import com.betwin.repository.HotGameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
@Slf4j
public class HotGameService {

    @Autowired
    private HotGameRepository hotGameRepository;

    public List<HotGame> findTopHotGames() {
        List<HotGame> hotGameList = new ArrayList<>();
        Sort sort = new Sort(Sort.Direction.DESC, "gameDate");
        Iterator<HotGame> iterator = hotGameRepository.findAll(PageRequest.of(0, BetWinConstants.MAX_PAGE_SIZE, sort)).iterator();
        while (iterator.hasNext()) {
            hotGameList.add(iterator.next());
        }
        return hotGameList;
    }

    public HotGame createHotGame(HotGame hotGame) {
        return hotGameRepository.save(hotGame);
    }
}
