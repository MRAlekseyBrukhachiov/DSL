import java.util.ArrayList;
import java.util.regex.Matcher;

public class Lexer {

    private String testCode;
    private ArrayList<Token> tokens = new ArrayList<Token>();

    public Lexer(String testCode) {
        this.testCode = testCode;
    }

    public void start() {
        for (String lexem: Lexems.lexems.keySet()) {

            Matcher m = Lexems.lexems.get(lexem).matcher(testCode);

            while (m.find()) {
                tokens.add(new Token(lexem, m.group()));
            }
        }

        tokens.forEach(System.out::println);
    }
}
