package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class GreaterEq extends RelExpr {

    public GreaterEq(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " >= " + r + ")";
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value l_v = l.eval(s);
        Value r_v = r.eval(s);
        if(l_v instanceof IntValue && r_v instanceof IntValue)
        {
            IntValue l_iv = (IntValue)l_v;
            IntValue r_iv = (IntValue)r_v;
            return new BoolValue((l_iv.n >= r_iv.n));
        }
        else
        {
            throw new RuntimeError(null);
        }
    }
}
