package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public abstract class ByteCode {
    protected String arg;

    public ByteCode() {};

    public abstract void init(String[] args);

    public abstract void execute(VirtualMachine vm);

    public abstract String getString();

    public abstract String getByteName();

}
