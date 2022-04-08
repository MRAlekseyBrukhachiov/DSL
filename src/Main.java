import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static String getCode() {
        String code = "";
        File file = new File("TestCode.txt");

        try (FileReader reader = new FileReader(file)) {
            int c;
            while((c = reader.read())!=-1){
                code = code.concat(Character.toString((char) c));
            }
        } catch (IOException e) {
            e.getMessage();
        }

        return code;
    }

    public static void main(String[] args) {
        String testCode = getCode();
        Lexems lex = new Lexems();
        Lexer lexer = new Lexer(testCode);
        lexer.start();

        System.out.println(testCode+ "\n");
        int len = 0;
        for (Token token: lexer.getTokens()) {
            if (token.getType() == "ENDL") {
                len++;
            }
        }
        Parser parser = new Parser(lexer.getTokens(), len);
        parser.lang();
        //lexer.start();
    }
}
