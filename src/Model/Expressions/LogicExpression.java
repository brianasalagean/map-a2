package Model.Expressions;

import Exceptions.InterpreterException;
import Model.Types.BoolType;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicExpression implements IExpression{
    IExpression expression1;
    IExpression expression2;
    String operation;

    public LogicExpression(String operation, IExpression expression1, IExpression expression2){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws InterpreterException {
        Value value1, value2;
        value1 = expression1.eval(symTable, heap);
        if (value1.getType().equals(new BoolType())) {
            value2 = expression2.eval(symTable, heap);
            if (value2.getType().equals(new BoolType())) {
                boolean bool1 = ((BoolValue)value1).getValue();
                boolean bool2 = ((BoolValue)value2).getValue();
                if (operation.equals("and"))
                    return new BoolValue(bool1 && bool2);
                if (operation.equals("or"))
                    return new BoolValue(bool1 || bool2);
            }
            else
                throw new InterpreterException("second operand is not boolean");
        }
        else
            throw new InterpreterException("first operand is not boolean");
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new LogicExpression(operation, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
