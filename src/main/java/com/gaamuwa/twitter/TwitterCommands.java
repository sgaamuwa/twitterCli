package com.gaamuwa.twitter;

import com.gaamuwa.twitter.services.TwitterService;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import twitter4j.Status;

import java.util.List;

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
        String status;


        @Override
        public void run(){
            // user should have specified that it is a new status and it shouldn't be empty
            Status returnedStatus = twitterService.sendTweet(status);
            System.out.print("-----------------------------------------------------------------------------------");
            System.out.printf("Status sent: %s\nTime: %s", returnedStatus.getText(), returnedStatus.getCreatedAt());
            System.out.print("-----------------------------------------------------------------------------------");
        }
    }

    @Component
    @Command(name = "timeline")
    public class TimelineCommand extends HelpAwarePicocliCommand {
        // user can specify that they want to see a particular user's timeline
        @Option(names = {"-u", "--user"})
        boolean user;

        @Option(names = {"-s", "--screenname"})
        String handle;

        @Override
        public void run(){
            // in a case where we have a user specified and a handle is not provided
            List<Status> statuses = null;
            if(user && handle == null){
                statuses = twitterService.getUserTimeline("");
            }
            // case where you have user specified and the handle is given
            else if(user && handle != null){
                statuses = twitterService.getUserTimeline(handle);
            }
            // if the user option is not used
            else if(!user){
                statuses = twitterService.getDefaultTimeline();
            }

            for(Status status : statuses){
                System.out.print("-----------------------------------------------------------------------------------\n");
                System.out.printf("Tweet: %s\nOwner: %s\nCreated At: %s\n Retweets: %d Favourites: %d\n",
                        status.getText(), status.getUser().getScreenName(), status.getCreatedAt(), status.getRetweetCount(), status.getFavoriteCount() );
                System.out.print("-----------------------------------------------------------------------------------\n");
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
