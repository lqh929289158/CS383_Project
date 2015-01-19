package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class AndAlso extends BinaryExpr {

    public AndAlso(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " andalso " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);
        if(tr1.t.equals(Type.BOOL) && tr2.t.equals(Type.BOOL))
        {
            return TypeResult.of(Type.BOOL);
        }
        throw new TypeError(null);
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value l_v = l.eval(s);
        Value r_v = r.eval(s);
        if(l_v instanceof BoolValue && r_v instanceof BoolValue)
        {
            BoolValue l_bv = (BoolValue)l_v;
            BoolValue r_bv = (BoolValue)r_v;
            return (new BoolValue(l_bv.b && (r_bv.b)));
        }
        else
            throw new RuntimeError(null);
        
    }
}
