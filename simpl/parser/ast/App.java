package simpl.parser.ast;

import simpl.interpreter.BoolValue;
import simpl.interpreter.ConsValue;
import simpl.interpreter.Env;
import simpl.interpreter.FunValue;
import simpl.interpreter.IntValue;
import simpl.interpreter.PairValue;
import simpl.interpreter.RuntimeError;
import simpl.interpreter.State;
import simpl.interpreter.Value;
import simpl.interpreter.lib.fst;
import simpl.interpreter.lib.hd;
import simpl.interpreter.lib.snd;
import simpl.interpreter.lib.tl;
import simpl.interpreter.pcf.iszero;
import simpl.interpreter.pcf.pred;
import simpl.interpreter.pcf.succ;
import simpl.parser.Symbol;
import simpl.typing.ArrowType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public class App extends BinaryExpr {

    public App(Expr l, Expr r) {
        super(l, r);
    }

    public String toString() {
        return "(" + l + " " + r + ")";
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
        TypeResult l_type = l.typecheck(E);
        Substitution sub = l_type.s;
        TypeVar a = new TypeVar(true);
        TypeVar b = new TypeVar(true);
        sub = l_type.t.unify(new ArrowType(a,b)).compose(sub);
        
        TypeResult r_type = r.typecheck(sub.compose(E));
        sub = r_type.s.compose(sub);
          
        sub = r_type.t.unify(sub.apply(a)).compose(sub);
        return TypeResult.of(sub,sub.apply(b));
     }

    @Override
    public Value eval(State s) throws RuntimeError {
        // TODO
        FunValue l_v = (FunValue)l.eval(s);
        Value r_v = r.eval(s);
        
        if(l_v instanceof fst){
            if(!(r_v instanceof PairValue))
                throw new RuntimeError("fst:Not pair");
            return ((PairValue)r_v).v1;
        }
        else if(l_v instanceof snd){
            if(!(r_v instanceof PairValue))
                throw new RuntimeError("snd:Not pair");
            return ((PairValue)r_v).v2;
        }
        else if(l_v instanceof hd){
            if(!(r_v instanceof ConsValue))
                throw new RuntimeError("hd:Not pair");
            return ((ConsValue)r_v).v1;
        }
        else if(l_v instanceof tl){
            if(!(r_v instanceof ConsValue))
                throw new RuntimeError("tl:Not pair");
            return ((ConsValue)r_v).v2;
        }
        else if(l_v instanceof iszero){
            if(!(r_v instanceof IntValue))
                throw new RuntimeError("iszero:Not Int");
            return new BoolValue(((IntValue)r_v).n==0);
        }
        else if(l_v instanceof pred){
            if(!(r_v instanceof IntValue))
                throw new RuntimeError("pred:Not Int");
            return new IntValue(((IntValue)r_v).n-1);
        }
        else if(l_v instanceof succ){
            if(!(r_v instanceof IntValue))
                throw new RuntimeError("succ:Not Int");
            return new IntValue(((IntValue)r_v).n+1);
        }
        return l_v.e.eval(State.of(new Env(l_v.E,l_v.x,r_v), s.M, s.p));
    }
}
