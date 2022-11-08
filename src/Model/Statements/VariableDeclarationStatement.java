package Model.Statements;

import Exceptions.InterpreterException;
import Model.State.ProgramState;
import Model.Types.Type;
import Model.Utils.MyIDictionary;
import Model.Values.Value;

public class VariableDeclarationStatement implements IStatement{
    String name;
    Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.containsKey(name)){
            throw new InterpreterException("Variable " + name + " already exists.");
        }
        symTable.put(name, type.defaultValue());
        state.setSymTable(symTable);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new VariableDeclarationStatement(name, type);
    }

    @Override
    public String toString() {
        return String.format("%s %s", type.toString(), name);
    }
}
