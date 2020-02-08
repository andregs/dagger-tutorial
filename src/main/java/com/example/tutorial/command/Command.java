package com.example.tutorial.command;

import java.util.List;
import java.util.Optional;

/** Logic to process some user input. */
public interface Command {

    /** Process the rest of the command's words and do something. */
    Result handleInput(List<String> input);

    class Result {
        private final Status status;
        private final Optional<CommandRouter> nestedCommandRouter;

        private Result(Status status, Optional<CommandRouter> nestedCommandRouter) {
            this.status = status;
            this.nestedCommandRouter = nestedCommandRouter;
        }

        public static Result invalid() {
            return new Result(Status.INVALID, Optional.empty());
        }

        public static Result handled() {
            return new Result(Status.HANDLED, Optional.empty());
        }

        public static Result inputCompleted() {
            return new Result(Status.INPUT_COMPLETED, Optional.empty());
        }

        public static Result enterNestedCommandSet(CommandRouter nestedCommandRouter) {
            return new Result(Status.HANDLED, Optional.of(nestedCommandRouter));
        }

        public Status status() {
            return status;
        }

        public Optional<CommandRouter> nestedCommandRouter() {
            return nestedCommandRouter;
        }
    }

    enum Status {
        INVALID,
        HANDLED,
        INPUT_COMPLETED,
    }

}
