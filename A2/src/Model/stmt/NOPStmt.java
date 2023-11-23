package Model.stmt;

import Model.PrgState;

public class NOPStmt implements IStmt{
    @Override
    public PrgState execute(PrgState state) throws Exception {
        return state;
    }

    @Override
    public String toString(){
        return "no operation";
    }
}
