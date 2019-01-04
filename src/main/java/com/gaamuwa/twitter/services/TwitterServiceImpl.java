package com.gaamuwa.twitter.services;

import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwitterServiceImpl implements TwitterService {

    Twitter twitter = new TwitterTemplate("", "", "", "");

    public void sendTweet(String status){
        Tweet tweet = twitter.timelineOperations().updateStatus(status);
        System.out.println(tweet.toString());
    }
}
