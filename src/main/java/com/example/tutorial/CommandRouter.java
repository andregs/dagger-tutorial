package com.example.tutorial;

import javax.inject.Inject;
import java.util.*;

final class CommandRouter {

    private final Map<String, Command> commands;

    @Inject
    public CommandRouter(Map<String, Command> commands) {
        // "hello" -> HelloWorldCommand
        // "login" -> LoginCommand
        this.commands = commands;
    }

    Command.Status route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        Command.Status status = command.handleInput(splitInput.subList(1, splitInput.size()));
        if (status == Command.Status.INVALID) {
            System.out.println(commandKey + ": invalid arguments");
        }
        return status;
    }

    private Command.Status invalidCommand(String input) {
        System.out.println(
                String.format("Couldn't understand \"%s\". Please try again.", input)
        );
        return Command.Status.INVALID;
    }

    private static List<String> split(String string) {
        return Arrays.asList(string.split(" "));
    }

}
