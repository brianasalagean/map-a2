package Controller;

import Exceptions.InterpreterException;
import Model.State.ProgramState;
import Model.Statements.IStatement;
import Model.Utils.MyIStack;
import Repository.IRepository;

import java.util.Objects;

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
        System.out.println(currentStatement.toString());
        state.setExeStack(stack);
        return currentStatement.execute(state);
    }

    public void allSteps() throws InterpreterException {
        ProgramState program = this.repository.getCurrentState();
        display();
        while (!program.getExeStack().isEmpty()){
            oneStep(program);
            display();
        }
    }

    public void setDisplayFlag(boolean value){
        displayFlag = value;
    }
    private void display() {
        if (displayFlag){
            System.out.println(this.repository.getCurrentState().toString());
        }
    }
}
