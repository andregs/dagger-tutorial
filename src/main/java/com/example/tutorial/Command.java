package com.example.tutorial;

import java.util.List;

/** Logic to process some user input. */
public interface Command {

    /** Process the rest of the command's words and do something. */
    Status handleInput(List<String> input);

    enum Status {
        INVALID,
        HANDLED,
    }

}
