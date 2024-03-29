import Operations.*;

import java.util.Stack;

public class Jorth {

    private static final String USAGE_INFO = "usage: java Jorth <file>";

    static Stack<Integer> stack;
    public static void main(String[] args) {
        // check for file to run via args[0]
        if (args.length < 1) {
            System.err.println(USAGE_INFO);
            return;
        }

        // run preprocessor, handle import, macros, comments
        Iterable<String> pre = Preprocessor.read(args[0]);
        if (pre == null) {
            System.err.println("File: " + args[0] + " cannot be processed");
            return;
        }

        // create execution stack from preprocessed tokens
        ExecutionStack es = new ExecutionStack();
        if (parseOperations(pre, es) != 0) {
            return;
        }

        // System.out.println(es);

        // execute programm
        executeProgram(es);
    }

    private static int parseOperations(Iterable<String> ops, ExecutionStack program) {
        for (String op : ops) {
            switch (op.toLowerCase()) {
                case "":
                    break;
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
                case GreaterThan.TOKEN:
                    program.push(new GreaterThan());
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
                case Else.TOKEN:
                    program.push(new Else());
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
                        System.err.println("Invalid token '" + op + "', cannot resolve to Operation, Number or Macro");
                        return 3;
                    }
                    break;
            }
        }
        return 0;
    }

    private static void executeProgram(ExecutionStack program) {
        program.execute();
    }
}