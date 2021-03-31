package com.task.gamesys.service;

import com.task.gamesys.dao.TweetDao;
import com.task.gamesys.dto.TweetDto;
import com.task.gamesys.model.Tweet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TweetServiceImpl implements TweetService {

    private final TweetDao tweetDao;

    @Autowired
    public TweetServiceImpl(TweetDao tweetDao) {
        this.tweetDao = tweetDao;
    }

    @Override
    public List<TweetDto> getLast10Replies() {
        return tweetDao.getLast10();
    }

    @Override
    public void saveUniqueFromList(List<Tweet> tweetList) {
        tweetDao.saveUniqueFromList(tweetList);
    }

}