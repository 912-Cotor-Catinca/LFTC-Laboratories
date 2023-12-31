package Model.stmt;

import Exceptions.DeclaredExceptions;
import Model.PrgState;
import Model.adt.IDict;
import Model.exp.Exp;
import Model.types.StringType;
import Model.value.IValue;
import Model.value.StringValue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStmt implements IStmt{
    private Exp exp;

    public OpenRFileStmt(Exp e){
        this.exp = e;
    }

    @Override
    public String toString(){
        return "(open file '" + this.exp + "')";
    }

    @Override
    public PrgState execute(PrgState state) throws Exception {
        IDict<StringValue, BufferedReader> fileTable = state.getFileTable();
        IDict<String, IValue> symTbl = state.getSymTable();
        IValue cond = this.exp.eval(symTbl);

        if(cond.getType().equals(new StringType()))
        {
            StringValue s = (StringValue) cond;
            if(!fileTable.containsKey(s))
            {
                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader(s.getvalue()));
                    fileTable.add(s, reader);
                }catch (IOException e){
                    throw new Exception(e.getMessage());
                }

            }
            else throw new Exception("File descriptor exists!");

        }else throw new DeclaredExceptions("The expression is not a string type");
        return state;
    }
}
