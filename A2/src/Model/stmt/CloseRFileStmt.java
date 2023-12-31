package Model.stmt;

import Exceptions.DeclaredExceptions;
import Model.PrgState;
import Model.adt.IDict;
import Model.exp.Exp;
import Model.types.StringType;
import Model.value.IValue;
import Model.value.StringValue;

import java.io.BufferedReader;

public class CloseRFileStmt implements IStmt{
    private final Exp exp;

    public CloseRFileStmt(Exp exp) {
        this.exp = exp;
    }


    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<String, IValue> symTbl = state.getSymTable();
        IDict<StringValue, BufferedReader> fileTbl = state.getFileTable();
        IValue cond = exp.eval(symTbl);

        if(cond.getType().equals(new StringType())){
            StringValue s = (StringValue) cond;
            if(fileTbl.containsKey(s)){
                BufferedReader reader = fileTbl.lookup(s);
                reader.close();
                fileTbl.remove(s);
            }else throw new DeclaredExceptions("unknown entry for file.");
        }else throw new DeclaredExceptions("the variable must be a string");

        return state;
    }

    @Override
    public String toString() {
        return "closeFile(" + this.exp.toString() + ")";
    }
}
