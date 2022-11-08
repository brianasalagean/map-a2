package Model.Statements;

import Exceptions.InterpreterException;
import Model.State.ProgramState;

public class NopStatement implements IStatement{
    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    @Override
    public String toString() {
        return "NopStatement";
    }
}
