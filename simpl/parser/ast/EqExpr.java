package simpl.parser.ast;

import simpl.typing.ListType;
import simpl.typing.PairType;
import simpl.typing.RefType;
import simpl.typing.Substitution;
import simpl.typing.Type;
import simpl.typing.TypeEnv;
import simpl.typing.TypeError;
import simpl.typing.TypeResult;
import simpl.typing.TypeVar;

public abstract class EqExpr extends BinaryExpr {

    public EqExpr(Expr l, Expr r) {
        super(l, r);
    }

    @Override
    public TypeResult typecheck(TypeEnv E) throws TypeError {
        // TODO
   /*     TypeResult tr1 = l.typecheck(E);
        TypeResult tr2 = r.typecheck(E);
        if(tr1.t.equals(Type.INT) && tr2.t.equals(Type.INT))
        {
          return TypeResult.of(Type.BOOL);   
        }
        throw new TypeError(null); */
        TypeResult l_type = l.typecheck(E);
        Substitution sub = l_type.s;
        
        TypeResult r_type = r.typecheck(sub.compose(E));
        sub = r_type.s.compose(sub);
        sub = r_type.t.unify(sub.apply(l_type.t)).compose(sub);
        if(!r_type.t.isEqualityType() || !l_type.t.isEqualityType())
            throw new TypeError(null);
        return TypeResult.of(sub,Type.BOOL);
        
    }
}
