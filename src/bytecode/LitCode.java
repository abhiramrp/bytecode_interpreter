package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class LitCode extends ByteCode{
    private int offset;

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
        vm.pushStack(offset);
    }

    @Override
    public String getString() {
        String b;

        if(varId.equals("")) {
            b = "LIT " + Integer.toString(offset);
            return b;
        }

        b = "LIT " + Integer.toString(offset) + " " + varId;
        String n = "int " + varId;

        return (b + "\t" + n);
    }

    @Override
    public String getByteName() {
        return "LIT";
    }
}
