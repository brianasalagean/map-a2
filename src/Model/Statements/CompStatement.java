package Model.Statements;

import Exceptions.InterpreterException;
import Model.State.ProgramState;
import Model.Utils.MyIStack;

public class CompStatement implements IStatement{
    IStatement firstStatement;
    IStatement secondStatement;

    public CompStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        state.setExeStack(stack);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new CompStatement(firstStatement.deepCopy(), secondStatement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("(%s|%s)", firstStatement.toString(), secondStatement.toString());
    }
}
