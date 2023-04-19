package bytecode;

import virtualmachine.VirtualMachine;

public class BopCode extends ByteCode{
    private String operation = "";

    @Override
    public void init(String[] args) {
        this.arg = String.join(" ", args);
        this.operation = args[1];

    }

    @Override
    public void execute(VirtualMachine vm) {
        int op2 = vm.popStack();
        int op1 = vm.popStack();

        int result = solve(op1, op2);

        vm.pushStack(result);
    }

    @Override
    public String getString() {
        return this.arg;
    }

    @Override
    public String getByteName() {
        return "BOP";
    }

    private int solve(int op1, int op2) {
        switch (operation) {
            case "+":
                return (op1 + op2);
            case "-":
                return (op1 - op2);
            case "*":
                return (op1 * op2);
            case "/":
                return (op1 / op2);
            case "==":
                if (op1 == op2) {
                    return 1;
                }
                break;
            case "!=":
                if (op1 != op2) {
                    return 1;
                }
                break;
            case "<=":
                if (op1 <= op2) {
                    return 1;
                }
                break;
            case ">":
                if (op1 > op2) {
                    return 1;
                }
                break;
            case ">=":
                if (op1 >= op2) {
                    return 1;
                }
                break;
            case "<":
                if (op1 < op2) {
                    return 1;
                }
                break;
            case "|":
                return (op1 | op2);
            case "&":
                return (op1 & op2);
            default:
                return 0;
        }

        return 0;
    }
}
