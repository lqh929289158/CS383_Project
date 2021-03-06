package simpl.parser.ast;

import simpl.interpreter.RefValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class Deref extends UnaryExpr {

    public Deref(Expr e) {
        super(e);
    }

    public String toString() {
        return "!" + e;
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult l_type = e.typecheck(E);
        Substitution sub = l_type.s;
        TypeVar a = new TypeVar(true);
        sub = l_type.t.unify(new RefType(a)).compose(sub);
        return TypeResult.of(sub,sub.apply(a));
    }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        Value l_side = e.eval(s);
        Value out = s.M.get(((RefValue)l_side).p);
        return out;
    }
}
