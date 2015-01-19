package simpl.interpreter;

public class ConsValue extends Value {

    public final Value v1, v2;

    public ConsValue(Value v1, Value v2) {
        this.v1 = v1;
        this.v2 = v2;
    }

    public String Length(){
        if(v2.equals(Value.NIL))
            return v1.Length();
        else
            return Integer.toString(Integer.parseInt(v1.Length()) + Integer.parseInt(v2.Length()));
    }
    
    public String toString() {
        // TODO
        return v1.toString() + "::" + v2.toString();
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if(!(other instanceof ConsValue)) return false;
        return ((((ConsValue)other).v1).equals(v1) && (((ConsValue)other).v2).equals(v2));
    }
}
