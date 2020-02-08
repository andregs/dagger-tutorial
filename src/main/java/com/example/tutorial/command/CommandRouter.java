package com.example.tutorial.command;

import com.example.tutorial.command.Command.Result;
import com.example.tutorial.command.Command.Status;
import com.example.tutorial.shared.Outputter;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class CommandRouter {

    private final Map<String, Command> commands;
    private final Outputter outputter;

    @Inject
    public CommandRouter(Map<String, Command> commands, Outputter outputter) {
        // "hello" -> HelloWorldCommand
        // "login" -> LoginCommand
        this.commands = commands;
        this.outputter = outputter;
    }

    Result route(String input) {
        List<String> splitInput = split(input);
        if (splitInput.isEmpty()) {
            return invalidCommand(input);
        }

        String commandKey = splitInput.get(0);
        Command command = commands.get(commandKey);
        if (command == null) {
            return invalidCommand(input);
        }

        List<String> args = splitInput.subList(1, splitInput.size());
        Result result = command.handleInput(args);
        return result.status().equals(Status.INVALID) ? invalidCommand(input) : result;
    }

    private Result invalidCommand(String input) {
        outputter.output(String.format("Couldn't understand \"%s\". Please try again.", input));
        return Result.invalid();
    }

    private static List<String> split(String string) {
        return Arrays.asList(string.split(" "));
    }

}
