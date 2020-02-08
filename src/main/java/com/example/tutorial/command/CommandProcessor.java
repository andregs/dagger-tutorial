package com.example.tutorial.command;

import com.example.tutorial.command.Command.Result;
import com.example.tutorial.command.Command.Status;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayDeque;
import java.util.Deque;

@Singleton
public final class CommandProcessor {
    private final Deque<CommandRouter> commandRouterStack = new ArrayDeque<>();

    @Inject
    public CommandProcessor(CommandRouter firstCommandRouter) {
        commandRouterStack.push(firstCommandRouter);
    }

    public Status process(String input) {
        Result result = commandRouterStack.peek().route(input);
        if (result.status().equals(Status.INPUT_COMPLETED)) {
            commandRouterStack.pop();
            return commandRouterStack.isEmpty()
                    ? Status.INPUT_COMPLETED
                    : Status.HANDLED;
        }

        result.nestedCommandRouter().ifPresent(commandRouterStack::push);
        return result.status();
    }

}
