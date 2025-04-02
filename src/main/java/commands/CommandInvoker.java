package commands;

import java.util.Stack;

/**
 * Command invoker that manages command execution and undo/redo functionality
 */
public class CommandInvoker {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public CommandInvoker() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void executeCommand(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
        }
    }
} 