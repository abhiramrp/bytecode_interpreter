package virtualmachine;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning;

    private boolean dumpStatus = false;

    public VirtualMachine(Program program) {
        this.program = program;
    }

    public void executeProgram() {
        programCounter = 0;
        runTimeStack = new RunTimeStack () ;
        returnAddress = new Stack < Integer >() ;
        isRunning = true ;

        while ( isRunning ) {
            ByteCode code = program.getCode(programCounter);

            //System.out.print(programCounter);
            //System.out.print(" ");
            //System.out.println(code.getByteName());

            code.execute(this);

            boolean d = code instanceof DumpCode;


            if(dumpStatus && d == false) {
                System.out.println(code.getString());
                runTimeStack.dump(); // check that the operation is correct
            }

            programCounter++;
        }

    }

    public void halt() {
        isRunning = false;
    }

    public void pushStack(int userInput) {
        runTimeStack.push(userInput);
    }

    public int popStack() {
        return runTimeStack.pop();
    }

    public int peekStack() {
        return runTimeStack.peek();
    }

    public void setPC(int addr) {
        programCounter = addr;
    }

    public void storeAtOffset(int offset) {
        runTimeStack.store(offset);
    }

    public void loadAtOffset(int offset) {
        runTimeStack.load(offset);
    }

    public void newFrame(int argNum) {
        runTimeStack.newFrameAt(argNum);
    }

    public void savePC() {
        returnAddress.add(programCounter);
    }

    public String getReturnAddress() {
        // System.out.print("In getReturn: ");
        // System.out.println(runTimeStack.getFramePtr());
        return runTimeStack.getFramePtr();
    }

    public void popFrame() {
        runTimeStack.popFrame();
    }

    public int popAddress() {
        return returnAddress.pop();
    }

    public void setDumpStatus(boolean dumpStat) {
        dumpStatus = dumpStat;
    }

    public int runTimeStackSize() {
        return this.runTimeStack.runTimeStackSize();
    }

    public void popStackValues(int popVals) {
        if(popVals >= this.runTimeStackSize()) {
            popVals = this.runTimeStackSize();
        }


        for (int i=0; i < popVals; i++) {
            this.popStack();
        }
    }
}
