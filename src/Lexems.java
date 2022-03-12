import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Lexems {

    public static Map<String, Pattern> lexems = new HashMap<String, Pattern>();

    public Lexems() {
        lexems.put("VAR", Pattern.compile("[a-z]\\w* ="));
        lexems.put("STRING", Pattern.compile("\".*\""));
        lexems.put("DIGIT", Pattern.compile("^0|[1-9][0-9]*"));
        lexems.put("ASSIGNMENT OPERATOR", Pattern.compile("="));
        lexems.put("PLUS", Pattern.compile("\\+"));
        lexems.put("MINUS", Pattern.compile("\\-"));
        lexems.put("MULT", Pattern.compile("\\*"));
        lexems.put("DIV", Pattern.compile("\\/"));
        lexems.put("IF", Pattern.compile("(?i)if"));
        lexems.put("FOR", Pattern.compile("(?i)for"));
        lexems.put("WHILE", Pattern.compile("(?i)while"));
        lexems.put("ELSE", Pattern.compile("(?i)else"));
        lexems.put("FUNC", Pattern.compile("[a-z]\\w*\\("));
    }
}
