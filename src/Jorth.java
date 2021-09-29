import java.util.Stack;

public class Jorth {

    static Stack<Integer> stack;
    public static void main(String[] args) {
        Iterable<String> pre = Preprocessor.read(args[0]);
        if (pre == null) {
            System.err.println("File: " + args[0] + " cannot be processed");
            return;
        }
        ExecutionStack es = new ExecutionStack();
        parseOperations(pre, es);
        executeProgram(es);        
    }

    private static void parseOperations(Iterable<String> ops, ExecutionStack program) {
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