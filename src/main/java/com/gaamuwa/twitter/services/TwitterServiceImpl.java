package com.gaamuwa.twitter.services;

import com.gaamuwa.twitter.exceptions.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.List;


@Service
public class TwitterServiceImpl implements TwitterService {

    @Autowired
    private Twitter twitter;

    @Override
    public Status sendTweet(String status){
        Status returnedStatus = null;
        // check that spaces are not passed in
        if(!status.trim().isEmpty()){
            try{
                returnedStatus  = twitter.updateStatus(status);
            }
            catch (TwitterException exception){
                // we need to get the status code and throw an exception to be handled with a better message
                exception.getStatusCode();
            }
        }else{
            throw new InvalidInputException("The status cannot be empty");
        }
       return returnedStatus;
    }

    @Override
    public Status sendTweet(String status, String mediaPath){
        Status returnedStatus = null;
        StatusUpdate statusUpdate = new StatusUpdate(status);
        File mediaFile = Paths.get(mediaPath).toFile();
        // if the file does not exist then throw a tantrum
        if(!mediaFile.exists()){
            throw new InvalidInputException("The file specified does not exist");
        }
        try{
            statusUpdate.setMedia(mediaFile);
            returnedStatus = twitter.updateStatus(statusUpdate);
        }catch (TwitterException exception){
            exception.getStatusCode();
        }
        return returnedStatus;
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
    public List<Status> getDefaultTimeline() {
        List<Status> statuses = null;
        try{
            statuses = twitter.getHomeTimeline();
        }catch (TwitterException exception){
            // this will also have to be subbed out
            throw new InvalidInputException("we received an exception when getting this");
        }finally {
            if(statuses == null){
                // make sure to swap this out with a more representative exception
                throw new InvalidInputException("We have null statuses");
            }
        }
        return statuses;
    }

    @Override
    public List<Status> getUserTimeline(String screenName) {
        List<Status> statuses = null;
        try{
            statuses = twitter.getUserTimeline(screenName);
        }catch (TwitterException exception){
            System.out.println("Failure getting timeline: " + exception.getMessage());
        }finally {
            if(statuses == null){
                throw new InvalidInputException("failed to retrieve the statuses");
            }
        }
        return statuses;
    }
}