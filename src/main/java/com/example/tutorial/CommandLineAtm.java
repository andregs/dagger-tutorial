package com.example.tutorial;

import java.util.Scanner;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CommandLineAtm {

    public static void main(String[] args) {
        CommandProcessor commandProcessor = CommandProcessorFactory.create().commandProcessor();
        Scanner scanner = new Scanner(System.in, UTF_8.name());

        while (scanner.hasNextLine()) {
            commandProcessor.process(scanner.nextLine());
        }
    }

}
