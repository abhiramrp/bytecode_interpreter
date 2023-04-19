package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    private ArrayList<Integer>  runTimeStack;
    private Stack<Integer> framePointer;

    private int argsCall;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Used for dumping the current state of the runTimeStack .
     * It will print portions of the stack based on respective
     * frame markers .
     * Example [1 ,2 ,3] [4 ,5 ,6] [7 ,8]
     * Frame pointers would be 0 ,3 ,6
     */
    public void dump () {
        /*
        System.out.print("Runtime: ");
        System.out.print(runTimeStack);

        System.out.print(" FramePointer: ");
        System.out.println(Arrays.toString(framePointer.toArray()));

         */

        Object[] frameArr = framePointer.toArray();
        int frameCounter = 0;

        System.out.print("[");

        for(int i=(int)frameArr[0]; i < runTimeStack.size(); i++) {
            System.out.print(runTimeStack.get(i));

            if(i+1 >= runTimeStackSize()) {
                break;
            }

            // if((int)i+1 == (int)frameArr[frameCounter]) {
            if(Arrays.asList(frameArr).contains(i)) {
                System.out.print("] [");
                frameCounter++;
            } else {
                System.out.print(",");
            }
        }

        System.out.println("]");

    }

    /**
     * returns the top of the runtime stack , but does not remove
     * @return copy of the top of the stack .
     */
    public int peek () {

        return runTimeStack.get(runTimeStack.size() - 1);
    }

    /**
     * push the value i to the top of the stack .
     * @param i value to be pushed .
     * @return value pushed
     */
    public int push ( int i ) {
        runTimeStack.add(i);
        return i;
    }

    /**
     * removes to the top of the runtime stack .
     * @return the value popped .
     */
    public int pop () {
        int i = this.peek();
        runTimeStack.remove(runTimeStack.size() - 1);
        return i;
    }

    /**
     * Takes the top item of the run time stack , and stores
     * it into a offset starting from the current frame .
     * @param offset number of slots above current frame marker
     * @return the item just stored
     */
    public int store ( int offsetFromFramePointer ) {
        int p = this.pop();

        int i = framePointer.peek() + offsetFromFramePointer;

        if(i > runTimeStack.size()) {
            runTimeStack.add(p);
            return p;

        }

        runTimeStack.set(i, p);

        return p;
    }

    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of
     * the stack
     * @param offset number of slots above current frame marker
     * @return item just loaded into the offset
     */
    public int load ( int offsetFromFramePointer ) {
        int l = runTimeStack.get(framePointer.peek() + offsetFromFramePointer);
        runTimeStack.add(l);

        return l;
    }

    /**
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack.
     * @param offset slots down from the top of the runtime stack
     */
    public void newFrameAt ( int offsetFromTopOfRunStack ) {
        argsCall = offsetFromTopOfRunStack;
        framePointer.push(runTimeStack.size() - offsetFromTopOfRunStack);
    }

    /**
     * pop the current frame off the runtime stack . Also removes
     * the frame pointer value from the FramePointer Stack .
     */
    public void popFrame () {
        int p = this.pop();
        int fp = framePointer.pop();

        for (int i=fp; i<runTimeStack.size(); i++) {
            this.pop();
        }

        this.push(p);
    }

    public String getFramePtr() {

        List<Integer> av = new ArrayList<>();

        int s = runTimeStack.size();

        if(s > argsCall) {
            av = this.runTimeStack.subList(s-argsCall, s);
            return av.toString().replace("[", "").replace("]","");
        }

        return this.runTimeStack.toString().replace("[", "").replace("]","");
    }

    /**
     * Returns the size of the stack
     * @return
     */
    public int runTimeStackSize() {
        return this.runTimeStack.size();
    }





}
