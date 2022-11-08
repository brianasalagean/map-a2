package View;

import Controller.Controller;
import Exceptions.InterpreterException;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.State.ProgramState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Utils.*;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Repository.IRepository;
import Model.Values.Value;
import Repository.Repository;

import java.util.Scanner;

public class View {
    public void runApp() {
        while (true){
            try {
                menu();
                Scanner read = new Scanner(System.in);
                int option = read.nextInt();
                if (option == 0){
                    break;
                }
                else if (option == 1){
                    runProgram1();
                }
                else if (option == 2){
                    runProgram2();
                }
                else if (option == 3){
                    runProgram3();
                }
                else {
                    System.out.println("Invalid input");
                }
            }
            catch (InterpreterException e){
                System.out.println("\n" + e.getMsg() + "\n");
            }
        }
    }

    private void menu(){
        System.out.println("Menu:\n");
        System.out.println("0: exit\n");
        System.out.println("1: run program 1: \nint v;\nv=5;\nPrint(v);\n");
        System.out.println("2: run program 2: \nint a;\nint b;\na=2*2+1\nb=a*2\nPrint(b);\n");
        System.out.println("3: run program 3: \nbool x;\nint y;\nx=true;\n(If x Then y=1 Else y=0);\nPrint(y);\n");
    }

    private void runProgram1() throws InterpreterException {
        IStatement expr1 = new CompStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(5))),
                        new PrintStatement(new VariableExpression("v"))));
        runStatement(expr1);
    }

    private void runProgram2() throws InterpreterException {
        IStatement expr2 = new CompStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompStatement(new AssignStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(1)),
                                new ArithmeticExpression('*', new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(2))))),
                        new CompStatement(new AssignStatement("b", new ArithmeticExpression('*', new VariableExpression("a"), new ValueExpression(new IntValue(2)))),
                                new PrintStatement(new VariableExpression("b"))))));
        runStatement(expr2);
    }

    private void runProgram3() throws InterpreterException {
        IStatement expr3 = new CompStatement(new VariableDeclarationStatement("x", new BoolType()),
                new CompStatement(new VariableDeclarationStatement("y", new IntType()),
                        new CompStatement(new AssignStatement("x", new ValueExpression(new BoolValue(true))),
                                new CompStatement(new IfStatement(new VariableExpression("x"),
                                        new AssignStatement("y", new ValueExpression(new IntValue(1))),
                                        new AssignStatement("y", new ValueExpression(new IntValue(0)))),
                                        new PrintStatement(new VariableExpression("y"))))));
        runStatement(expr3);
    }

    private void runStatement(IStatement statement) throws InterpreterException {
        MyIStack<IStatement> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap heap = new MyHeap();

        ProgramState state = new ProgramState(exeStack, symTable, output, heap, statement);
        state.setExeStack(exeStack);
        IRepository repository = new Repository(state);
        Controller controller = new Controller(repository, false);

        System.out.println("display steps?[Yes/No]");
        Scanner read = new Scanner(System.in);
        String option = read.next();
        controller.setDisplayFlag(option.equals("Yes"));

        controller.allSteps();
        System.out.println("Result: " + state.getOutput().toString().replace('[', ' ').replace(']', ' '));
    }
}
