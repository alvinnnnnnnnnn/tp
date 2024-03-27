package command;
import activeedge.task.TaskList;
import activeedge.ui.CommandUi;

public class ClearCommand {

    public ClearCommand() {
        // No input needed for this command
    }

    public void execute() {
        if (TaskList.TASKS_LIST.isEmpty()) {
            CommandUi.printDataAlreadyClearedMessage();
        } else {
            TaskList.clearTasks();
            CommandUi.printAllTasksClearedMessage();
        }
    }
}
