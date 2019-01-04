package com.gaamuwa.twitter;

import com.gaamuwa.twitter.services.TwitterService;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

@Component
@Command(name = "twitter")
public class TwitterCommands extends HelpAwarePicocliCommand {

    @Autowired
    private TwitterService twitterService;


    @Parameters(paramLabel = "STATUS", description = "status")
    String status;

    @Override
    public void run(){
        if(!status.isEmpty()){
            twitterService.sendTweet(status);
            System.out.println(status);
        }
    }
}
