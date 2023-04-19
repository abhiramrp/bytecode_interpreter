package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;

import java.util.Scanner;

public class ReadCode extends ByteCode{
    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
    }

    @Override
    public void execute(VirtualMachine vm) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Please enter an integer: ");
        while (!sc.hasNextInt()) {
            System.out.print("Please enter an integer: ");
            sc.next();
        }

        vm.pushStack(sc.nextInt());

        sc.close();

    }

    @Override
    public String getString() {

        return this.getByteName();
    }

    @Override
    public String getByteName() {
        return "READ";
    }
}
