package com.gaamuwa.twitter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

@Configuration
public class SocialConfig {

    @Bean
    public Twitter getTwiterInstance() {
        // This will set up the twitter auth. Should return a singleton that we use through the app
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(System.getenv("TWITTER_CONSUMER_KEY"))
                .setOAuthConsumerSecret(System.getenv("TWITTER_CONSUMER_SECRET"))
                .setOAuthAccessToken(System.getenv("TWITTER_ACCESS_TOKEN"))
                .setOAuthAccessTokenSecret(System.getenv("TWITTER_ACCESS_SECRET"));
        TwitterFactory tf = new TwitterFactory(cb.build());
        System.out.println("This is what I want to see: " + System.getenv("TWITTER_ACCESS_TOKEN"));
        Twitter twitter = tf.getInstance();
        return twitter;
    }
}
