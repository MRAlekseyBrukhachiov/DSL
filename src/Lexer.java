import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private String testCode;
    private ArrayList<Token> tokens = new ArrayList<Token>();

    private final Map<String, Pattern> lexems = Map.of(
            "VAR", Pattern.compile("[a-z_]\\w*"),
            "DIGIT", Pattern.compile("^0|[1-9][0-9]*"),
            "ASSIGN_OP", Pattern.compile("="),
            "OP", Pattern.compile("\\-|\\+|\\*|\\/"),
            "L_BC", Pattern.compile("\\("),
            "R_BC", Pattern.compile("\\)"),
            "ENDL", Pattern.compile("\\;")
    );

    public Lexer(String testCode) {
        this.testCode = testCode;
    }

    private Token addToken(String val) {
        for (String lexem : lexems.keySet()) {
            Matcher m = lexems.get(lexem).matcher(val);
            if (m.find()) {
                return new Token(lexem, m.group());
            }
        }
        throw new IllegalArgumentException("Illegal value: " + val);
    }

    public void start() {
        String[] words = testCode.split("\\s+");
        for (String word: words) {
            tokens.add(addToken(word));
        }
        tokens.forEach(System.out::println);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
