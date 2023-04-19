package bytecode;

import virtualmachine.VirtualMachine;

public class ReturnCode extends ByteCode {
    private String funcfull = "";
    private String funcname = "";

    private int rVal;

    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);

        if (args.length > 1) {
            funcfull = args[1];
            funcname = args[1].split("<<", 2)[0];
        }
    }

    @Override
    public void execute(VirtualMachine vm) {
        rVal = vm.peekStack();
        vm.popFrame();
        vm.setPC(vm.popAddress());

    }

    @Override
    public String getString() {
        String b = "RETURN " + funcfull;

        if (funcfull.equals("")) {
            b = "RETURN " + funcfull;
        }


        String n = "EXIT " + funcname + ": " + rVal;


        return (b + "\t" + n);
    }

    @Override
    public String getByteName() {
        return "RETURN";
    }
}
