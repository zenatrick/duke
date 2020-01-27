package seedu.duke.command;

import java.util.ArrayList;

import seedu.duke.exception.TaskIndexOutOfBoundException;
import seedu.duke.task.Task;
import seedu.duke.task.TaskList;

import static seedu.duke.common.Messages.EMPTY_FIND_RESULT_MSG;

class FindCommand implements Command {
    private final String keyword;

    FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        int listSize = taskList.size();
        ArrayList<String> responseBuilder = new ArrayList<>();
        responseBuilder.add("Here are the matching tasks in your list: ");
        for (int i = 0; i < listSize; i++) {
            try {
                Task currentTask = taskList.get(i);
                if (currentTask.descriptionContains(keyword)) {
                    responseBuilder.add(String.format("%d.%s", i + 1, currentTask));
                }
            } catch (TaskIndexOutOfBoundException e) {
                throw new AssertionError("Task index will never be out of bound.");
            }
        }

        if (responseBuilder.size() == 1) {
            return new CommandResponse(EMPTY_FIND_RESULT_MSG);
        }
        return new CommandResponse(responseBuilder.toArray(new String[0]));
    }
}
