package simpl.interpreter;

class NilValue extends Value {

    protected NilValue() {
    }

    public String toString() {
        return "nil";
    }

    @Override
    public boolean equals(Object other) {
        // TODO
        return (other instanceof NilValue);
    }

    @Override
    public String Length() {
        // TODO Auto-generated method stub
        return null;
    }
}
