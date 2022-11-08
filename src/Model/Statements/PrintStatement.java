package Model.Statements;

import Exceptions.InterpreterException;
import Model.Expressions.IExpression;
import Model.State.ProgramState;
import Model.Utils.MyIList;
import Model.Values.Value;

public class PrintStatement implements IStatement{
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIList<Value> out = state.getOutput();
        out.add(expression.eval(state.getSymTable(), state.getHeap()));
        state.setOutput(out);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("Print(%s)", expression.toString());
    }
}
