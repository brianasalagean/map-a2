package Repository;

import Model.State.ProgramState;

import java.util.List;

public interface IRepository {
    List<ProgramState> getProgramList();
    void setProgramState(List<ProgramState> programStates);
    ProgramState getCurrentState();
    void addProgram(ProgramState program);
}
