import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private String testCode;
    private ArrayList<Token> tokens = new ArrayList<Token>();

    private Map<String, Pattern> lexems = new HashMap<>();

    public Lexer(String testCode) {
        this.testCode = testCode;
        lexems.put("VAR", Pattern.compile("[a-z_]\\w*"));
        lexems.put("DIGIT", Pattern.compile("^0|[1-9][0-9]*"));
        lexems.put("ASSIGN_OP", Pattern.compile("^=$"));
        lexems.put("OP", Pattern.compile("\\-|\\+|\\*|\\/"));
        lexems.put("L_BC", Pattern.compile("\\("));
        lexems.put("R_BC", Pattern.compile("\\)"));
        lexems.put("ENDL", Pattern.compile("\\;"));
        lexems.put("COMPARE_OP", Pattern.compile("==|<|>|!="));
        lexems.put("IF", Pattern.compile("^IF$"));
        lexems.put("ELSE", Pattern.compile("^ELSE$"));
        lexems.put("WHILE", Pattern.compile("^WHILE$"));
        lexems.put("DO", Pattern.compile("^DO$"));
        lexems.put("FOR", Pattern.compile("^FOR$"));
        lexems.put("ELSE", Pattern.compile("^ELSE$"));
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
