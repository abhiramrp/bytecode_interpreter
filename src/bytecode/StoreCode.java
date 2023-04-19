package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class StoreCode extends ByteCode {
    private int offset;
    private int top;

    private String varId = "";
    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);

        this.offset = Integer.parseInt(args[1]);

        if (args.length > 2) {
            this.varId = args[2];
        }

    }

    @Override
    public void execute(VirtualMachine vm) {
        top = vm.popStack();
        vm.storeAtOffset(offset);
    }

    @Override
    public String getByteName() {
        return "STORE";
    }

    @Override
    public String getString() {
        String b = "STORE " + Integer.toString(offset) + " " + varId;

        String n = varId + " = " + Integer.toString(top);

        return (b + "\t" + n);
    }
}
