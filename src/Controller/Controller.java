package Controller;

import Exceptions.InterpreterException;
import Model.State.ProgramState;
import Model.Statements.IStatement;
import Model.Utils.MyIStack;
import Repository.IRepository;

import java.util.EmptyStackException;

public class Controller {
    IRepository repository;
    boolean displayFlag;

    public Controller(IRepository repository, boolean value) {
        this.repository = repository;
        this.displayFlag = value;
    }

    public ProgramState oneStep(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExeStack();
        if (stack.isEmpty())
            throw new InterpreterException("Program state stack is empty");
        IStatement currentStatement = stack.pop();
        return currentStatement.execute(state);
    }

    public void oneStepExecutionOneStep(ProgramState state, boolean flag) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExeStack();
        if (stack.isEmpty()) {
            throw new InterpreterException("The execution stack is empty");
        }
        IStatement currentStatement = stack.pop();
        currentStatement.execute(state);
        if (flag) {
            display();
        }
    }

    public void allSteps() throws InterpreterException {
        ProgramState program = this.repository.getCurrentState();
        display();
        while (!program.getExeStack().isEmpty()) {
            oneStep(program);
            display();
        }
    }

    public void setDisplayFlag(boolean value) {
        displayFlag = value;
    }

    private void display() {
        System.out.println(this.repository.getCurrentState().toString());
    }

    public ProgramState getCurrentProgramState() {
        return this.repository.getCurrentState();
    }
}
