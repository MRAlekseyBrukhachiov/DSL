import java.util.Map;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Lexems {

    public static Map<String, Pattern> lexems = new HashMap<String, Pattern>();

    public Lexems() {
        lexems.put("VAR", Pattern.compile("[a-z]\\w*"));
        lexems.put("DIGIT", Pattern.compile("^0|[1-9][0-9]*"));
        lexems.put("ASSIGN_OP", Pattern.compile("="));
        lexems.put("OP", Pattern.compile("\\-|\\+|\\*|\\/"));
        lexems.put("L_BC", Pattern.compile("\\("));
        lexems.put("R_BC", Pattern.compile("\\)"));
        lexems.put("ENDL", Pattern.compile("\\;"));
    }
}
