package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

public class CallCode extends ResolveCode {
    private String label = "";

    private String addresses = "";
    private int addr;
    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
        this.label = args[1];
    }

    @Override
    public void execute(VirtualMachine vm) {
        vm.savePC();
        vm.setPC(addr);

        addresses = vm.getReturnAddress();
    }

    @Override
    public String getString() {
        String b = "CALL" + " " + label;

        String n = label.split("<<", 2)[0] + "(" + addresses + ")";

        return (b + "\t" + n);
    }

    @Override
    public String getByteName() {
        return "CALL";
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
