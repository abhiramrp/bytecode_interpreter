package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class GotoCode extends ResolveCode {
    private String label;
    private int addr = 0;

    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
        this.label = args[1];

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setPC(addr);
    }

    @Override
    public String getString() {
        return this.arg;
    }

    @Override
    public String getByteName() {
        return "GOTO";
    }


    @Override
    public void setAddr(int addr) {
        this.addr = addr;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
