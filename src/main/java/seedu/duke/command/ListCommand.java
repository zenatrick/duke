package seedu.duke.command;

import seedu.duke.common.Messages;
import seedu.duke.exception.TaskIndexOutOfBoundException;
import seedu.duke.task.TaskList;

class ListCommand implements Command {

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        int listSize = taskList.size();
        if (listSize == 0) {
            return new CommandResponse(Messages.EMPTY_TASK_LIST_MSG);
        } else {
            String[] response = new String[listSize + 1];
            response[0] = "Here are the tasks in your list: ";
            for (int i = 0; i < listSize; i++) {
                try {
                    response[i + 1] = String.format("%d.%s", i + 1, taskList.get(i));
                } catch (TaskIndexOutOfBoundException e) {
                    throw new AssertionError("Task index will never be out of bound.");
                }
            }
            return new CommandResponse(response);
        }
    }
}
