package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class HaltCode extends ByteCode {


    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.halt();
    }

    @Override
    public String getString() {
        return this.arg;
    }

    @Override
    public String getByteName() {
        return "HALT";
    }
}
