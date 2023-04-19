

import virtualmachine.Program;
import bytecode.ByteCode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public final class ByteCodeLoader {

    private BufferedReader byteSource;
    private ArrayList<ByteCode> byteCodeList;

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN loadCodes.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
        this.byteCodeList = new ArrayList<>();
    }
    /**
     * This function should read one line of source code at a time.
     * For each read line:
     *      Tokenize string to break it into parts. Can also use the split function in the String class.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     *      Then add newly created and initialize ByteCode to the program
     */
    public Program loadCodes() {

        try {
            String l = "";
            while (byteSource.ready()) {
                l = byteSource.readLine();
                String splitLine[] = l.split("\\s+");
                // ByteCode and Argument
                addByteCode(splitLine[0], splitLine);
            }
        } catch(IOException e){
            System.out.println ("io error");
            e.printStackTrace();
        }

        Program p = new Program(byteCodeList);

        p.resolveAddress();

        return p;
    }

    private void addByteCode(String byteName, String[] argName) {

        String byteClass = CodeTable.getClassName(byteName);


        try {
            ByteCode bytecode = (ByteCode) (Class.forName("interpreter.bytecode."+byteClass).getDeclaredConstructor().newInstance());

            bytecode.init(argName);
            byteCodeList.add(bytecode);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
