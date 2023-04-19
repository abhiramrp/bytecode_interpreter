package bytecode;

import virtualmachine.VirtualMachine;

public class PopCode extends ByteCode{
    private int popVals;

    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
        this.popVals = Integer.parseInt(args[1]);
    }

    @Override
    public void execute(VirtualMachine vm) {

        vm.popStackValues(popVals);
    }

    @Override
    public String getString() {
        return this.arg;
    }

    @Override
    public String getByteName() {
        return "POP";
    }
}
