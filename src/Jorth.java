import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Jorth {

    private static final String COMMENT_IDENTIFIER = "//";

    static Stack<Integer> stack;
    public static void main(String[] args) {
        ExecutionStack es = parseFile(new File(args[0]));
        executeProgram(es);        
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
            parseLine(s.nextLine(), program);
        }
        s.close();
        return program;
    }

    private static void parseLine(String s, ExecutionStack program) {
        String[] ops = s.split(" ");
        for (String op : ops) {
            switch (op.toLowerCase()) {
            case Print.TOKEN:
                program.push(new Print());
                break;
            case Plus.TOKEN:
                program.push(new Plus());
                break;
            case Minus.TOKEN:
                program.push(new Minus());
                break;
            case Mult.TOKEN:
                program.push(new Mult());
                 break;
            case Mod.TOKEN:
                program.push(new Mod());
                break;
            case Equal.TOKEN:
                program.push(new Equal());
                break;
            case LessThan.TOKEN:
                program.push(new LessThan());
                break;
            case Dup.TOKEN:
                program.push(new Dup());
                break;
            case Swap.TOKEN:
                program.push(new Swap());
                break;
            case Over.TOKEN:
                program.push(new Over());
                break;
            case Drop.TOKEN:
                program.push(new Drop());
                break;
            case If.TOKEN:
                program.push(new If());
                break;
            case End.TOKEN:
                program.push(new End());
                break;
            case While.TOKEN:
                program.push(new While());
                break;
            case Do.TOKEN:
                program.push(new Do());
                break;
            case COMMENT_IDENTIFIER:
                return;
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
    
    private static void executeProgram(ExecutionStack program) {
        program.execute();
    }
}