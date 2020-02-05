package com.example.tutorial;

import java.util.Scanner;

public class CommandLineAtm {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // this factory is auto-generated
        CommandRouterFactory commandRouterFactory = DaggerCommandRouterFactory.create();

        CommandRouter commandRouter = commandRouterFactory.router();

        while (scanner.hasNextLine()) {
            commandRouter.route(scanner.nextLine());
        }
    }

}
