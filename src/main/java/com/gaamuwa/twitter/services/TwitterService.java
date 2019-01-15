package com.gaamuwa.twitter.services;

import twitter4j.Status;

import java.nio.file.Path;
import java.util.List;

public interface TwitterService {
    /**
     * Send a tweet with only a status
     * @param status
     */
    Status sendTweet(String status);

    /**
     * Send a tweet with a media file as well
     * @param status
     * @param mediaPath
     */
    Status sendTweet(String status, String mediaPath);

    /**
     * Send a direct message to someone with the twitter handle
     * @param twitterHandle
     * @param message
     */
    void sendDirectMessage(String twitterHandle, String message);

    void viewDirectMessages();

    /**
     * Gets the user's home timeline
     */
    List<Status> getDefaultTimeline();

    List<Status> getUserTimeline(String screenname);

}
