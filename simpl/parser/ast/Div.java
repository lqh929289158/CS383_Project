package simpl.parser.ast;

import simpl.interpreter.IntValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;

public class Div extends ArithExpr {

    public Div(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " / " + r + ")";
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
            if(r_iv.n != 0)
                return new IntValue((l_iv.n + r_iv.n));
            else
                throw new RuntimeError("Divide-by-0.");
        }
        else
        {
            throw new RuntimeError(null);
        }
    }
}
