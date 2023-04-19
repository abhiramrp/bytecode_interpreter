package virtualmachine;

import bytecode.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Program {

    private List<ByteCode> program;

    public Program(ArrayList<ByteCode> codes)
    {
        program = new ArrayList<>(codes);
    }

    public int getByteCodeVectorSize() {
        return this.program.size();
    }

    protected ByteCode getCode(int programCounter) {
        return this.program.get(programCounter);
    }

    public void resolveAddress() {
        HashMap<String, Integer> addresses = new HashMap<>();

        for (int i=0; i < program.size(); i++) {

            ByteCode bytecode = program.get(i);

            if (bytecode instanceof LabelCode) {
                String s = ((LabelCode) bytecode).getLabel();
                addresses.put(s, i);
            }

        }

        for (int i=0; i < program.size(); i++) {

            ByteCode bytecode = program.get(i);

            if (bytecode instanceof ResolveCode) {
                // System.out.println(((ResolveCode) bytecode).getByteName());

                if( addresses.get(((ResolveCode) bytecode).getLabel()) != null) {
                    int addr = addresses.get(((ResolveCode) bytecode).getLabel());
                    ((ResolveCode) bytecode).setAddr(addr);
                }
            }

        }

    }

    public void printCodes() {
        for (int i=0; i < program.size(); i++) {

            ByteCode bytecode = program.get(i);

            System.out.println(bytecode.getString());

        }

    }




}
