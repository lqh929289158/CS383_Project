package simpl.parser.ast;

import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.PairType;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;

public class Pair extends BinaryExpr {

    public Pair(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(pair " + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);
        return TypeResult.of(new PairType(tr1.t,tr2.t));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        return new PairValue(l.eval(s),r.eval(s));
    }
}
