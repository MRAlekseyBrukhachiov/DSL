import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private final String testCode;
    private final ArrayList<Token> tokens = new ArrayList<>();
    private static final Map<String, Pattern> lexemes = new HashMap<>();

    public Lexer(String testCode) {
        this.testCode = testCode;
        run();
    }

    static {
        lexemes.put("VAR", Pattern.compile("^[a-z_]\\w*$"));
        lexemes.put("DIGIT", Pattern.compile("^\\d*$"));
        lexemes.put("ASSIGN_OP", Pattern.compile("^=$"));
        lexemes.put("OP", Pattern.compile("^(-|\\+|\\*|/)$"));
        lexemes.put("L_BC", Pattern.compile("^\\($"));
        lexemes.put("R_BC", Pattern.compile("^\\)$"));
        lexemes.put("ENDL", Pattern.compile("^;$"));
        lexemes.put("COMPARE_OP", Pattern.compile("^(~|<|>|!=)$"));
        lexemes.put("IF", Pattern.compile("^IF$"));
        lexemes.put("ELSE", Pattern.compile("^ELSE$"));
        lexemes.put("WHILE", Pattern.compile("^WHILE$"));
        lexemes.put("DO", Pattern.compile("^DO$"));
        lexemes.put("FOR", Pattern.compile("^FOR$"));
        lexemes.put("DIV", Pattern.compile("^,$"));
        lexemes.put("PRINT", Pattern.compile("^PRINT$"));
    }

    private void run() {
        String tokenStart = "";
        for (int i = 0; i < testCode.length(); i++) {

            if (testCode.toCharArray()[i] == ' ') {
                continue;
            }

            tokenStart += testCode.toCharArray()[i];
            String tokenEnd = " ";

            if (i < testCode.length() - 1) {
                tokenEnd = tokenStart + testCode.toCharArray()[i + 1];
            }

            for (String key: lexemes.keySet()) {
                Pattern p = lexemes.get(key);
                Matcher m_1 = p.matcher(tokenStart);
                Matcher m_2 = p.matcher(tokenEnd);

                if (m_1.find() && !m_2.find()) {
                    tokens.add(new Token(key, tokenStart));
                    tokenStart = "";
                    break;
                }
            }
        }
        tokens.forEach(System.out::println);
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }
}
