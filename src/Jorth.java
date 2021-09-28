import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import operations.*;

public class Jorth {

    static Stack<Integer> stack;
    public static void main(String[] args) {
        executeProgram(parseFile(new File(args[0])));        
    }

    private static Iterable<Operation> parseFile(File f) {
        if (!f.exists() || !f.isFile()) {
            throw new RuntimeException("File: " + f + " not found!");
        }

        LinkedList<Operation> program = new LinkedList<>();

        Scanner s;
        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }
        while (s.hasNextLine()) {
            String[] ops = s.nextLine().split(" ");
            for (String op : ops) {
                switch (op) {
                    case ".":
                        program.add(new Dump());
                        break;
                    case "+":
                        program.add(new Plus());
                        break;
                    case "*":
                        program.add(new Mult());
                        break;
                    default:
                        try {
                            int a = Integer.parseInt(op);
                            program.add(new Immediate(a));
                        } catch (Exception e) {
                            //TODO: handle exception
                        }
                        break;
                }
            }
        }
        s.close();
        return program;
    }

    private static void executeProgram(Iterable<Operation> program) {
        stack = new Stack<>();
        for (Operation op : program) {
            op.execute(stack);
        }
    }
}