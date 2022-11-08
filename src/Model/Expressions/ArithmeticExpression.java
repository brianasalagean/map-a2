package Model.Expressions;

import Exceptions.InterpreterException;
import Model.Types.IntType;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IntValue;
import Model.Values.Value;

public class ArithmeticExpression implements IExpression{
    IExpression expression1;
    IExpression expression2;
    char operation;

    public ArithmeticExpression(char operation, IExpression expression1, IExpression expression2){
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws InterpreterException {
        Value value1, value2;
        value1 = expression1.eval(symTable, heap);
        if (value1.getType().equals(new IntType())){
            value2 = expression2.eval(symTable, heap);
            if (value2.getType().equals(new IntType())){
                int nr1 = ((IntValue)value1).getValue();
                int nr2 = ((IntValue)value2).getValue();
                if (operation == '+')
                    return new IntValue(nr1 + nr2);
                if (operation == '-')
                    return new IntValue(nr1 - nr2);
                if (operation == '*')
                    return new IntValue(nr1 * nr2);
                if (operation == '/') {
                    if (nr2 == 0)
                        throw new InterpreterException("division by zero.");
                    return new IntValue(nr1 / nr2);
                }
            }
            else
                throw new InterpreterException("second operand is not an integer.");
        }
        else
            throw new InterpreterException("first operand is not an integer.");
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new ArithmeticExpression(operation, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
