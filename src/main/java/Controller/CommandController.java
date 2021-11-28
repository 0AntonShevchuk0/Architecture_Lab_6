package Controller;

import View.IGeometryView;

public class CommandController {
    private CommandHistory history;

    private IGeometryView cgv;

    public CommandHistory getHistory() {
        return history;
    }

    public void execute(ICommand command) {
        command.execute();
        history.push(command);
    }

    public CommandController(int historySize, IGeometryView cgv) {
        history = new CommandHistory(historySize);
        this.cgv = cgv;
    }
}
