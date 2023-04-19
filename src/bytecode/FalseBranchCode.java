package bytecode;

import virtualmachine.VirtualMachine;

public class FalseBranchCode extends ResolveCode{
    private String label;
    private int addr;
    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
        this.label = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        if(vm.popStack() == 0) {
            vm.setPC(addr);
        }
    }

    public String getString() {
        return "FALSEBRANCH " + Integer.toString(addr);
    };

    @Override
    public String getByteName() {
        return "FALSEBRANCH";
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
