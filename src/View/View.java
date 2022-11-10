package View;

import Controller.Controller;
import Exceptions.InterpreterException;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.State.ProgramState;
import Model.Statements.AssignStatement;
import Model.Statements.CompStatement;
import Model.Statements.IStatement;
import Model.Statements.IfStatement;
import Model.Statements.PrintStatement;
import Model.Statements.VariableDeclarationStatement;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Utils.MyDictionary;
import Model.Utils.MyHeap;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Utils.MyIList;
import Model.Utils.MyIStack;
import Model.Utils.MyList;
import Model.Utils.MyStack;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;
import Repository.IRepository;
import Repository.Repository;

import java.util.Scanner;

public class View {

    private final boolean displayFlag;

    public View(boolean displayFlag) {
        this.displayFlag = displayFlag;
    }

    public void runApp() throws InterpreterException {
        if (!displayFlag) {
            System.out.println("Display flag set to false -> run all steps");
            chooseMenuForAllStep();
        } else {
            System.out.println("Display flag set to true -> run one step");
            chooseMenuForOneStep();
        }
    }

    private void chooseMenuForOneStep() throws InterpreterException {
        Controller c1 = runProgram1();
        Controller c2 = runProgram2();
        Controller c3 = runProgram3();
        while (true) {
            try {
                menu();
                Scanner read = new Scanner(System.in);
                int option = read.nextInt();
                if (option == 0) {
                    break;
                } else if (option == 1) {
                    c1.oneStepExecutionOneStep(c1.getCurrentProgramState(), displayFlag);
                } else if (option == 2) {
                    c2.oneStepExecutionOneStep(c2.getCurrentProgramState(), displayFlag);
                } else if (option == 3) {
                    c3.oneStepExecutionOneStep(c3.getCurrentProgramState(), displayFlag);
                } else {
                    System.out.println("Invalid input");
                }
            } catch (InterpreterException e) {
                System.out.println("\n" + e.getMsg() + "\n");
            }
        }
    }

    private void chooseMenuForAllStep() {
        while (true) {
            try {
                menu();
                Scanner read = new Scanner(System.in);
                int option = read.nextInt();
                if (option == 0) {
                    break;
                } else if (option == 1) {
                    runProgram1();
                } else if (option == 2) {
                    runProgram2();
                } else if (option == 3) {
                    runProgram3();
                } else {
                    System.out.println("Invalid input");
                }
            } catch (InterpreterException e) {
                System.out.println("\n" + e.getMsg() + "\n");
            }
        }
    }

    private void menu() {
        System.out.println("Menu:\n");
        System.out.println("0: exit\n");
        System.out.println("1: run program 1: \nint v;\nv=5;\nPrint(v);\n");
        System.out.println("2: run program 2: \nint a;\nint b;\na=2*2+1\nb=a*2\nPrint(b);\n");
        System.out.println("3: run program 3: \nbool x;\nint y;\nx=true;\n(If x Then y=1 Else y=0);\nPrint(y);\n");
    }

    private Controller runProgram1() throws InterpreterException {

        MyIStack<IStatement> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap heap = new MyHeap();

        IStatement expr1 = new CompStatement(new VariableDeclarationStatement("v", new IntType()),
                new CompStatement(new AssignStatement("v", new ValueExpression(new IntValue(5))),
                        new PrintStatement(new VariableExpression("v"))));

        ProgramState state = new ProgramState(exeStack, symTable, output, heap, expr1);
        IRepository repository = new Repository(state);
        Controller controller = new Controller(repository, this.displayFlag);

        if (!this.displayFlag) {
            runStatement(controller);
        }
        return controller;
    }

    private Controller runProgram2() throws InterpreterException {

        MyIStack<IStatement> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap heap = new MyHeap();

        IStatement expr2 = new CompStatement(new VariableDeclarationStatement("a", new IntType()),
                new CompStatement(new VariableDeclarationStatement("b", new IntType()),
                        new CompStatement(new AssignStatement("a", new ArithmeticExpression('+', new ValueExpression(new IntValue(1)),
                                new ArithmeticExpression('*', new ValueExpression(new IntValue(2)), new ValueExpression(new IntValue(2))))),
                                new CompStatement(new AssignStatement("b", new ArithmeticExpression('*', new VariableExpression("a"), new ValueExpression(new IntValue(2)))),
                                        new PrintStatement(new VariableExpression("b"))))));

        ProgramState state = new ProgramState(exeStack, symTable, output, heap, expr2);
        IRepository repository = new Repository(state);
        Controller controller = new Controller(repository, this.displayFlag);

        if (!this.displayFlag) {
            runStatement(controller);
        }
        return controller;
    }

    private Controller runProgram3() throws InterpreterException {

        MyIStack<IStatement> exeStack = new MyStack<>();
        MyIDictionary<String, Value> symTable = new MyDictionary<>();
        MyIList<Value> output = new MyList<>();
        MyIHeap heap = new MyHeap();

        IStatement expr3 = new CompStatement(new VariableDeclarationStatement("x", new BoolType()),
                new CompStatement(new VariableDeclarationStatement("y", new IntType()),
                        new CompStatement(new AssignStatement("x", new ValueExpression(new BoolValue(true))),
                                new CompStatement(new IfStatement(new VariableExpression("x"),
                                        new AssignStatement("y", new ValueExpression(new IntValue(1))),
                                        new AssignStatement("y", new ValueExpression(new IntValue(0)))),
                                        new PrintStatement(new VariableExpression("y"))))));

        ProgramState state = new ProgramState(exeStack, symTable, output, heap, expr3);
        IRepository repository = new Repository(state);
        Controller controller = new Controller(repository, this.displayFlag);

        if (!this.displayFlag) {
            runStatement(controller);
        }
        return controller;
    }

    private void runStatement(Controller controller) throws InterpreterException {
        controller.allSteps();
    }
}
