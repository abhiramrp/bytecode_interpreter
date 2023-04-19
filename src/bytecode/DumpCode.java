package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class DumpCode extends ByteCode {
    private String dumps = "";
    private boolean dumpStat = false;
    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);

        dumps = args[1];

        if(dumps.equals("ON")) {
            dumpStat = true;
        } else {
            dumpStat = false;
        }

    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.setDumpStatus(dumpStat);
    }

    @Override
    public String getString() {
        return this.arg;
    }

    @Override
    public String getByteName() {
        return "DUMP";
    }
}
