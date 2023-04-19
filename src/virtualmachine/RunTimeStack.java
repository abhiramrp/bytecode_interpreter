package virtualmachine;

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

    public int peek () {

        return runTimeStack.get(runTimeStack.size() - 1);
    }

    public int push ( int i ) {
        runTimeStack.add(i);
        return i;
    }

    public int pop () {
        int i = this.peek();
        runTimeStack.remove(runTimeStack.size() - 1);
        return i;
    }

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

    public int load ( int offsetFromFramePointer ) {
        int l = runTimeStack.get(framePointer.peek() + offsetFromFramePointer);
        runTimeStack.add(l);

        return l;
    }

    public void newFrameAt ( int offsetFromTopOfRunStack ) {
        argsCall = offsetFromTopOfRunStack;
        framePointer.push(runTimeStack.size() - offsetFromTopOfRunStack);
    }

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


    public int runTimeStackSize() {
        return this.runTimeStack.size();
    }





}
