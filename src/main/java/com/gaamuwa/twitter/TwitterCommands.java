package com.gaamuwa.twitter;

import com.gaamuwa.twitter.services.TwitterService;
import com.kakawait.spring.boot.picocli.autoconfigure.ExitStatus;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

@Component
public class TwitterCommands {

    @Autowired
    private TwitterService twitterService;


    @Component
    @Command(name = "status")
    public class StatusCommand extends HelpAwarePicocliCommand {
        @CommandLine.Option(
                names = {"-n", "--new"}
        )
        boolean newStatus;

        @Parameters(paramLabel = "STATUS", description = "status")
        String status;

        @Override
        public void run(){
            if(!status.isEmpty() && newStatus){
                twitterService.sendTweet(status);
                System.out.println(status);
            }
        }
    }

    @Component
    @Command(name = "timeline")
    public class TimelineCommand extends HelpAwarePicocliCommand {

        @Override
        public void run(){
            twitterService.getDefaultTimeline();
        }

    }

}
