package simpl.interpreter;

public class RefValue extends Value {

    public final int p;

    public RefValue(int p) {
        this.p = p;
    }

    public String toString() {
        return "ref@" + p;
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        if(!(other instanceof RefValue)) return false;
        return (((RefValue)other).p == p);
    }

    @Override
    public String Length() {
        // TODO Auto-generated method stub
        return null;
    }
}
