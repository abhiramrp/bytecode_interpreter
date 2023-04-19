package bytecode;

import virtualmachine.VirtualMachine;

public class ArgsCode extends ByteCode{
    private int argNum;

    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
        argNum = Integer.parseInt(args[1]);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.newFrame(argNum);
    }

    @Override
    public String getString() {
        return this.arg;
    }

    @Override
    public String getByteName() {
        return "ARGS";
    }
}
