package bytecode;


public abstract class ResolveCode extends ByteCode {
    public abstract void setAddr(int addr);

    public abstract String getLabel();
}
