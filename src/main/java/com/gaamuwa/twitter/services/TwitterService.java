package com.gaamuwa.twitter.services;

import twitter4j.TwitterException;

import java.nio.file.Path;

public interface TwitterService {
    /**
     * Send a tweet with only a status
     * @param status
     */
    void sendTweet(String status);

    /**
     * Send a tweet with a media file as well
     * @param status
     * @param mediaPath
     */
    void sendTweet(String status, Path mediaPath);

    /**
     * Send a direct message to someone with the twitter handle
     * @param twitterHandle
     * @param message
     */
    void sendDirectMessage(String twitterHandle, String message);

    /**
     * Gets the user's home timeline
     */
    void getDefaultTimeline();

}
