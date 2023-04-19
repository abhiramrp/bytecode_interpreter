package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class LabelCode extends ByteCode {
    private String label;
    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
        this.label = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        return;
    }

    @Override
    public String getString() {
        return this.arg;
    }

    public String getLabel() {
        return this.label;
    }

    @Override
    public String getByteName() {
        return "LABEL";
    }
}
