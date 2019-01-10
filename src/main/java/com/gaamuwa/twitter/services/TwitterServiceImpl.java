package com.gaamuwa.twitter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.nio.file.Path;
import java.util.List;


@Service
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    private Twitter twitter;

    @Override
    public void sendTweet(String status){
        try{
            Status returnedStatus  = twitter.updateStatus(status);
            System.out.println("Status updated: " + returnedStatus.getText());
            System.out.println("♥ : " + returnedStatus.getFavoriteCount());
        }
        catch (TwitterException exception){
            System.out.println("There was a twitter exception");
            System.out.println(exception.getErrorMessage());
        }
    }

    @Override
    public void sendTweet(String status, Path mediaPath){
        StatusUpdate statusUpdate = new StatusUpdate(status);
        try{
            statusUpdate.setMedia(mediaPath.toFile());
            Status returnedStatus = twitter.updateStatus(statusUpdate);
            System.out.println("Status Updated: "+ returnedStatus.getText());
        }catch (TwitterException exception){
            System.out.println("There was a twitter exception");
        }
    }

    @Override
    public void sendDirectMessage(String twitterHandle, String message) {
        try{
            DirectMessage directMessage = twitter.sendDirectMessage(twitterHandle, message);
            System.out.println(String.format("Message: %s \n Sent to: %s", directMessage.getText(), directMessage.getRecipientScreenName()));
        }catch (TwitterException exception){
            System.out.println("Failure sending direct message to: " + twitterHandle );
            System.out.println("Error message: "+ exception.getMessage());
        }
    }

    @Override
    public void viewDirectMessages() {
        try{
            List<DirectMessage> directMessages = twitter.getDirectMessages();
            for(DirectMessage directMessage: directMessages){
                System.out.println(String.format("Message: %s \n Sent By: %s", directMessage.getText(), directMessage.getSenderScreenName()));
            }
        }catch (TwitterException exception){
            System.out.println("Failure receiving DirectMessages");
            System.out.println("Error message: "+ exception.getMessage());
        }
    }

    @Override
    public void getDefaultTimeline() {
        try{
            List<Status> statuses = twitter.getHomeTimeline();
            for (Status status : statuses){
                System.out.println(String.format("%s \nBy: @%s \n\n", status.getText(), status.getUser().getScreenName()));
                System.out.println("---------------------------------------------------");
            }
        }catch (TwitterException exception){
            System.out.println("Failure getting timeline: " + exception.getMessage());
        }
    }

    @Override
    public void getUserTimeline(String screenName) {
        try{
            List<Status> statuses = twitter.getUserTimeline(screenName);
            for (Status status : statuses){
                System.out.println(String.format("%s \nBy: @%s \n\n", status.getText(), status.getUser().getScreenName()));
                System.out.println("---------------------------------------------------");
            }
        }catch (TwitterException exception){
            System.out.println("Failure getting timeline: " + exception.getMessage());
        }
    }
}