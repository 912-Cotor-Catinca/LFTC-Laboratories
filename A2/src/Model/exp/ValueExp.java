package Model.exp;

import Model.adt.IDict;
import Model.types.BoolType;
import Model.types.IntType;
import Model.types.StringType;
import Model.value.IValue;

public class ValueExp extends Exp{
    private IValue val;
    public ValueExp(IValue val){
        this.val = val;
    }
    @Override
    public IValue eval(IDict<String, IValue> symTable) throws Exception {
        if(this.val.getType().equals(new IntType()) || this.val.getType().equals(new BoolType())||
            this.val.getType().equals(new StringType()))
            return this.val;
        else
            throw new Exception("Unknown type\n");
    }

    @Override
    public String toString() {
        return this.val.toString();
    }
}
