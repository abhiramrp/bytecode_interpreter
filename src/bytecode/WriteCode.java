package bytecode;

import virtualmachine.VirtualMachine;

public class WriteCode extends ByteCode{

    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
    }

    @Override
    public void execute(VirtualMachine vm) {
        System.out.println(vm.peekStack());
    }

    @Override
    public String getString() {
        return this.getByteName();
    }

    @Override
    public String getByteName() {
        return "WRITE";
    }
}
