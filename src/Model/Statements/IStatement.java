package Model.Statements;

import Exceptions.InterpreterException;
import Model.State.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws InterpreterException;
    IStatement deepCopy();
}
