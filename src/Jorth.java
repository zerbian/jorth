import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Jorth {

    static Stack<Integer> stack;
    public static void main(String[] args) {
        executeProgram(parseFile(new File(args[0])));        
    }

    private static ExecutionStack parseFile(File f) {
        if (!f.exists() || !f.isFile()) {
            throw new RuntimeException("File: " + f + " not found!");
        }

        ExecutionStack program = new ExecutionStack();

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
                switch (op.toLowerCase()) {
                    case ".":
                        program.push(new Dump());
                        break;
                    case "+":
                        program.push(new Plus());
                        break;
                    case "*":
                        program.push(new Mult());
                        break;
                    case "=":
                        program.push(new Equal());
                        break;
                    case "<":
                        program.push(new LessThan());
                        break;
                    case "dup":
                        program.push(new Dup());
                        break;
                    case "swap":
                        program.push(new Swap());
                        break;
                    case "if":
                        program.push(new If());
                        break;
                    case "end":
                        program.push(new End());
                        break;
                    case "while":
                        program.push(new While());
                        break;
                    case "do":
                        program.push(new Do());
                        break;
                    default:
                        try {
                            int a = Integer.parseInt(op);
                            program.push(new Immediate(a));
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

    private static void executeProgram(ExecutionStack program) {
        program.execute();
    }
}