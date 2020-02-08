package com.example.tutorial;

import com.example.tutorial.command.CommandProcessor;

import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CommandLineAtm {

    public static void main(String[] args) {
        CommandProcessor commandProcessor = CommandProcessorComponent.create().commandProcessor();
        Scanner scanner = new Scanner(System.in, UTF_8.name());

        while (scanner.hasNextLine()) {
            commandProcessor.process(scanner.nextLine());
        }
    }

}
