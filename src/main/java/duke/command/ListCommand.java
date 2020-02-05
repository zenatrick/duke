package duke.command;

import duke.exception.TaskIndexOutOfBoundException;
import duke.task.TaskList;

import static duke.common.Messages.EMPTY_TASK_LIST_MSG;

class ListCommand implements Command {
    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public CommandResponse execute(TaskList taskList) {
        int listSize = taskList.size();
        if (listSize == 0) {
            return new CommandResponse(EMPTY_TASK_LIST_MSG);
        } else {
            String[] response = new String[listSize + 1];
            response[0] = "Here are the tasks in your list: ";
            for (int i = 0; i < listSize; i++) {
                try {
                    response[i + 1] = String.format("%d.%s", i + 1, taskList.get(i));
                } catch (TaskIndexOutOfBoundException e) {
                    assert false : e;
                }
            }
            return new CommandResponse(response);
        }
    }
}
