import java.util.ArrayList;
import java.util.regex.Matcher;

public class Lexer {

    private String testCode;
    private ArrayList<Token> tokens = new ArrayList<Token>();

    public Lexer(String testCode) {
        this.testCode = testCode;
    }

    private Token addToken(String val) {
        for (String lexem : Lexems.lexems.keySet()) {
            Matcher m = Lexems.lexems.get(lexem).matcher(val);
            if (m.find()) {
                return new Token(lexem, m.group());
            }
        }
        return null;
    }

    public void start() {
        String[] words = testCode.split("\\s+");
        for (String word: words) {
            tokens.add(addToken(word));
        }
        tokens.forEach(System.out::println);
    }
}
