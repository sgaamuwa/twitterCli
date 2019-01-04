package com.gaamuwa.twitter;

import com.kakawait.spring.boot.picocli.autoconfigure.ExitStatus;
import com.kakawait.spring.boot.picocli.autoconfigure.HelpAwarePicocliCommand;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@SpringBootApplication
public class TwitterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TwitterApplication.class, args);
    }

    @Component
    @Command
    static class MainCommand extends HelpAwarePicocliCommand{
        @Option(names = {"-v", "--version"}, description = "display version info")
        boolean versionRequested;

        @Override
        public ExitStatus call() {
            if (versionRequested) {
                System.out.println("0.1.0");
                return ExitStatus.TERMINATION;
            }
            return ExitStatus.OK;
        }
    }
}

