package simpl.parser.ast;

import simpl.interpreter.RecValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.parser.Symbol;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Name extends Expr {

    public Symbol x;

    public Name(Symbol x) {
        this.x = x;
    }

    public String toString() {
        return "" + x;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
       
        Type tmp = E.get(x);
        if(tmp == null)
        {       
            return TypeResult.of(new TypeVar(true));
        }
        else
        {
            return TypeResult.of(tmp);
        }
   
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        
        Value v_out = s.E.get(x);
        if(v_out==null)
            throw new RuntimeError(null);
        else if(v_out instanceof RecValue){
            RecValue vv = (RecValue)v_out;
            Expr tmp = new Rec(vv.x,vv.e);
            return tmp.eval(State.of(vv.E, s.M, s.p));
        }
        else
            return v_out;
    }
}
