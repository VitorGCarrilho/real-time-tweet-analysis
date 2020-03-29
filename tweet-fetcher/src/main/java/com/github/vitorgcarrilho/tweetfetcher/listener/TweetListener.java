package com.github.vitorgcarrilho.tweetfetcher.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 28/03/2020
 */
@Component
@RequiredArgsConstructor
public class TweetListener implements CommandLineRunner {

    private final StatusListener statusListener;

    private final TwitterStreamFactory twitterStreamFactory;

    @Override
    public void run(String... args) throws Exception  {
        TwitterStream twitterStream = twitterStreamFactory.getInstance();
        twitterStream.addListener(statusListener);
        twitterStream.sample();
    }
}
