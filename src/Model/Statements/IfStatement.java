package Model.Statements;

import Exceptions.InterpreterException;
import Model.Expressions.IExpression;
import Model.State.ProgramState;
import Model.Utils.MyIStack;
import Model.Values.BoolValue;
import Model.Values.Value;

public class IfStatement implements IStatement{
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        Value result = expression.eval(state.getSymTable(), state.getHeap());
        if (result instanceof BoolValue boolResult) {
            IStatement statement;
            if (boolResult.getValue()) {
                statement = thenStatement;
            }
            else
                statement = elseStatement;
            MyIStack<IStatement> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return state;
        }
        else
            throw new InterpreterException("Provide boolean expression for if statement.");

    }

    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("if{%s}{%s}else{%s}", expression.toString(), thenStatement.toString(), elseStatement.toString());
    }
}
