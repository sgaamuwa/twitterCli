package com.gaamuwa.twitter;

import com.gaamuwa.twitter.services.TwitterService;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;

@Component
public class TwitterCommands {

    @Autowired
    private TwitterService twitterService;


    @Component
    @Command(name = "status")
    public class StatusCommand extends HelpAwarePicocliCommand {
        // user can specify that it is a new status
        @Option(
                names = {"-n", "--new"}
        )
        boolean newStatus;

        // status provided by the user for the new status
        @Parameters(paramLabel = "STATUS", description = "status")
        String status;

        @Override
        public void run(){
            // user should have specified that it is a new status and it shouldn't be empty
            if(!status.trim().isEmpty() && newStatus){
                twitterService.sendTweet(status);
                System.out.println(status);
            }
        }
    }

    @Component
    @Command(name = "timeline")
    public class TimelineCommand extends HelpAwarePicocliCommand {
        // user can specify that they want to see a particular user's timeline
        @Option(names = {"-u", "--user"})
        boolean user;

        @Parameters(paramLabel = "SCREENNAME", description = "screenname for the person whose timeline were are retrieving", defaultValue = "user")
        String screenName;

        @Override
        public void run(){
            if(user && !screenName.trim().isEmpty()){
                twitterService.getUserTimeline(screenName);
            }
            // if the user option is not provided then get the default timeline
            else if(!user){
                twitterService.getDefaultTimeline();
            }
        }
    }

    @Component
    @Command(name = "dm")
    public class DirectMessageCommand extends HelpAwarePicocliCommand {

        @Option(names = {"-v", "--view"})
        boolean view;

        @Override
        public void run(){
            if(view){
                twitterService.viewDirectMessages();
            }
        }
    }

}
