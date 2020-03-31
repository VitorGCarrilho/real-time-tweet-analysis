package com.github.vitorgcarrilho.tweetfetcher.listener;

import com.github.vitorgcarrilho.tweetfetcher.producer.TweetProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Vitor Carrilho
 * @since 28/03/2020
 */
@Component
public class StatusTwitterListener implements StatusListener {

    private static final Logger logger = LoggerFactory.getLogger(StatusTwitterListener.class);

    @Autowired
    private TweetProducer tweetProducer;

    @Override
    public void onStatus(Status status) {
        logger.info("posting new status");
        tweetProducer.sendMessage("@" + status.getUser().getScreenName() + " - Location:"+status.getUser().getLocation()+ " - " + status.getText());
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
        logger.debug("Got a status deletion notice id: {}", statusDeletionNotice.getStatusId());
    }

    @Override
    public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
        logger.debug("Got track limitation notice: {}", numberOfLimitedStatuses);
    }

    @Override
    public void onScrubGeo(long userId, long upToStatusId) {
        logger.debug("Got scrub_geo event userId: {} upToStatusId: {}", userId, upToStatusId);
    }

    @Override
    public void onStallWarning(StallWarning warning) {
        logger.debug("Got stall warning: {}", warning);
    }

    @Override
    public void onException(Exception ex) {
        ex.printStackTrace();
    }
}
