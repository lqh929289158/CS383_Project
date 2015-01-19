package simpl.interpreter;

public class BoolValue extends Value {

    public final boolean b;

    public BoolValue(boolean b) {
        this.b = b;
    }

    public String toString() {
        return "" + b;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if(!(other instanceof BoolValue)) return false;
        return (((BoolValue)other).b == b);
    }

    @Override
    public String Length() {
        // TODO Auto-generated method stub
        return "1";
    }
}
