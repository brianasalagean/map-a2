package Repository;

import Model.State.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private int current;

    public Repository(ProgramState programState){
        this.programStates = new ArrayList<>();
        this.current = 0;
        this.addProgram(programState);
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.programStates;
    }

    @Override
    public void setProgramState(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

    @Override
    public ProgramState getCurrentState() {
        return this.programStates.get(current);
    }

    @Override
    public void addProgram(ProgramState program) {
        this.programStates.add(program);
    }
}
