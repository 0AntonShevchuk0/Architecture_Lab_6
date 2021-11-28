package Controller;

import java.util.List;

public class CommandHistory {
    private ICommand[] history;
    private int index;

    public void push(ICommand command) {
        if (index != history.length) {
            history[index++] = command;
        }
    }

    public void pop() {
        if (index != 0) {
            history[--index].undo();
        }
    }

    public CommandHistory(int size) {
        history = new ICommand[size];
        index = 0;
    }
}
