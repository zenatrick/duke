package seedu.duke.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import seedu.duke.common.DateTimeFormat;
import seedu.duke.common.Messages;
import seedu.duke.exception.IncorrectCommandException;

public class CommandParser {
    public static Command parse(String userInputCommand) throws IncorrectCommandException {
        String[] tokens = userInputCommand.split("\\s+");
        String commandWord = tokens[0];

        if (commandWord.equals("done")) {
            // Mark as done command
            if (tokens.length != 2) {
                // Command is invalid if the number of arguments not exactly 1.
                throw new IncorrectCommandException(
                        Messages.generateDoneErrorMessage(Messages.INVALID_INDEX_ERROR_MSG));
            }
            try {
                int taskIndex = Integer.parseInt(tokens[1]) - 1; // May throw NumberFormatException
                return new MarkAsDoneCommand(taskIndex);
            } catch (NumberFormatException e) {
                // Command is invalid if the argument is not a valid integer.
                throw new IncorrectCommandException(
                        Messages.generateDoneErrorMessage(Messages.INVALID_INDEX_ERROR_MSG));
            }
        } else if (commandWord.equals("delete")) {
            // Delete command
            if (tokens.length != 2) {
                // Command is invalid if the number of arguments not exactly 1.
                throw new IncorrectCommandException(
                        Messages.generateDeleteErrorMessage(Messages.INVALID_INDEX_ERROR_MSG));
            }
            try {
                int taskIndex = Integer.parseInt(tokens[1]) - 1; // May throw NumberFormatException
                return new DeleteCommand(taskIndex);
            } catch (NumberFormatException e) {
                // Command is invalid if the argument is not a valid integer.
                throw new IncorrectCommandException(
                        Messages.generateDeleteErrorMessage(Messages.INVALID_INDEX_ERROR_MSG));
            }
        } else if (commandWord.equals("todo")
                || commandWord.equals("deadline")
                || commandWord.equals("event")) {
            // Add command
            return parseAddCommandFromTokens(tokens);
        } else if (userInputCommand.equals("list")) {
            return new ListCommand();
        } else if (userInputCommand.equals("bye")) {
            return new ExitCommand();
        } else {
            // Invalid commands
            throw new IncorrectCommandException("I'm sorry, but I don't know what that means.");
        }
    }


    private static AddCommand parseAddCommandFromTokens(String[] tokens) throws IncorrectCommandException {
        String keyword = tokens[0];
        StringBuilder descriptionBuilder = new StringBuilder();
        StringBuilder timeBuilder = new StringBuilder();
        boolean isDescDone = false;
        String delimiter;
        switch (keyword) {
        case "deadline":
            delimiter = "/by";
            break;
        case "event":
            delimiter = "/at";
            break;
        default:
            delimiter = "";
        }

        for (int i = 1; i < tokens.length; i++) {
            if (tokens[i].equals(delimiter)) {
                isDescDone = true;
            } else if (isDescDone) {
                timeBuilder.append(tokens[i]).append(" ");
            } else {
                descriptionBuilder.append(tokens[i]).append(" ");
            }
        }

        String description = descriptionBuilder.toString().stripTrailing();
        String timeString = timeBuilder.toString().stripTrailing();

        switch (keyword) {
        case "todo":
            if (description.isBlank()) {
                throw new IncorrectCommandException(
                        Messages.generateAddTodoErrorMessage(Messages.EMPTY_DESCRIPTION_ERROR_MSG));
            }
            return new AddCommand(AddCommand.TYPE_TODO, description, null);
        case "deadline":
            if (description.isBlank()) {
                throw new IncorrectCommandException(
                        Messages.generateAddDeadlineErrorMessage(Messages.EMPTY_DESCRIPTION_ERROR_MSG));
            } else if (timeString.isBlank()) {
                throw new IncorrectCommandException(
                        Messages.generateAddDeadlineErrorMessage(Messages.EMPTY_TIME_ERROR_MSG));
            }

            try {
                LocalDateTime time = LocalDateTime.parse(timeString, DateTimeFormat.INPUT_DATE_TIME_FORMAT);
                return new AddCommand(AddCommand.TYPE_DEADLINE, description, time);
            } catch (DateTimeParseException e) {
                throw new IncorrectCommandException(Messages.generateTimeFormatErrorMessage());
            }
        case "event":
            if (description.isBlank()) {
                throw new IncorrectCommandException(
                        Messages.generateAddEventErrorMessage(Messages.EMPTY_DESCRIPTION_ERROR_MSG));
            } else if (timeString.isBlank()) {
                throw new IncorrectCommandException(
                        Messages.generateAddEventErrorMessage(Messages.EMPTY_TIME_ERROR_MSG));
            }

            try {
                LocalDateTime time = LocalDateTime.parse(timeString, DateTimeFormat.INPUT_DATE_TIME_FORMAT);
                return new AddCommand(AddCommand.TYPE_EVENT, description, time);
            } catch (DateTimeParseException e) {
                throw new IncorrectCommandException(Messages.generateTimeFormatErrorMessage());
            }
        default: // Never reached
            throw new AssertionError("Invalid commands have already been accounted for.");
        }
    }
}
