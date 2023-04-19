

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


    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
        this.byteCodeList = new ArrayList<>();
    }

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
            ByteCode bytecode = (ByteCode) (Class.forName("bytecode."+byteClass).getDeclaredConstructor().newInstance());

            bytecode.init(argName);
            byteCodeList.add(bytecode);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
