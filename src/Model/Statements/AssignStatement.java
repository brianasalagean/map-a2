package Model.Statements;

import Exceptions.InterpreterException;
import Model.Expressions.IExpression;
import Model.State.ProgramState;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.Value;

public class AssignStatement implements IStatement{
    private final String key;
    private final IExpression expression;

    public AssignStatement(String key, IExpression expression) {
        this.key = key;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIDictionary<String, Value> symbolTable = state.getSymTable();
        if (symbolTable.containsKey(key)) {
            Value value = expression.eval(symbolTable, state.getHeap());
            Type typeId = (symbolTable.get(key)).getType();
            if (value.getType().equals(typeId)) {
                symbolTable.put(key, value);
            }
            else
                throw new InterpreterException("Declared type of variable " + key + " and type of the assigned expression don't match\n");
        }
        else
            throw new InterpreterException("The used variable " + key + "was not declared\n");
        state.setSymTable(symbolTable);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new AssignStatement(key, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("%s=%s", key, expression.toString());
    }
}
