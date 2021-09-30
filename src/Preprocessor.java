import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Preprocessor {

    public static final String COMMENT_IDENTIFIER = "//";
    public static final String MACRO_IDENTIFIER_START = "macro";
    public static final String MACRO_IDENTIFIER_END = "macro_end";
    public static final String IMPORT_IDENTIFIER = "include";

    private static final Map<String,LinkedList<String>> macros = new HashMap<>();

    public static Iterable<String> read(String filename) {
        File f = new File(filename);
        if (!f.exists() || !f.isFile()) return null;

        Scanner scan;
        try {
            scan = new Scanner(f);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        LinkedList<String> operations = new LinkedList<>();
        while (scan.hasNextLine()) {
            parseLine(scan, operations);
        }

        return operations;
    }

    private static void parseLine(Scanner scan, LinkedList<String> operations) {
        String line = scan.nextLine();
        String[] tokens = line.split(" ");
        int i = 0;
        while (i < tokens.length) {
            String token = tokens[i];
            switch (token.toLowerCase()) {
                case COMMENT_IDENTIFIER:
                    return;
                case IMPORT_IDENTIFIER:
                    //TODO handle file import
                    return;
                case MACRO_IDENTIFIER_START:
                    registerMacro(tokens[i+1], scan);
                    i++;
                    break;
                default:
                    if (macros.containsKey(token)) {
                        operations.addAll(macros.get(token));
                    } else {
                        operations.add(token);
                    }
                    break;
            }
            i++;
        }
    }

    private static void registerMacro(String macroName, Scanner scan) {
        LinkedList<String> macroOps = new LinkedList<>();
        do {
            String line = scan.nextLine();
            String[] tokens = line.split(" ");
            for (String token : tokens) {
                switch (token) {
                    case COMMENT_IDENTIFIER:
                        return;
                    case MACRO_IDENTIFIER_END:
                        macros.put(macroName, macroOps);
                        return;
                    default:
                        // allow macros inside of macros
                        if (macros.containsKey(token)) {
                            macroOps.addAll(macros.get(token));
                        } else {
                            macroOps.add(token);
                        }
                        break;
                }
            }

        } while (scan.hasNextLine());
    }
}
