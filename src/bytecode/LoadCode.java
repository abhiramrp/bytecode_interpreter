package bytecode;

import virtualmachine.VirtualMachine;
public class LoadCode extends ByteCode {
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
        vm.loadAtOffset(offset);
    }

    @Override
    public String getByteName() {
        return "LOAD";
    }

    @Override
    public String getString() {
        String b = "LOAD " + Integer.toString(offset) + " " + varId;
        String n = "<load " + varId + ">";

        return (b + "\t" + n);
    }
}
