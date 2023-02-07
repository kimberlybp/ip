package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {
    private CommandType type;

    public Command(CommandType type) {
        this.type = type;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isBye() {
        return type.equals(CommandType.BYE);
    }

    public CommandType getType() {
        return type;
    }
}