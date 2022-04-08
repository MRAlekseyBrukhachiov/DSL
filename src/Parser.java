/*VAR -> "[a-z]\\w*"
DIGIT -> "(0|[1-9])[0-9]*"
ASSIGN_OP -> "="
OP -> "[-|+|/|*]"
L_BC -> "("
R_BC -> ")"
ENDL -> ";"

lang -> expr+
expr -> VAR ASSIGN_OP expr_value ENDL
expr_value -> value (OP value)*
value -> (VAR | DIGIT) | infinity
infinity -> L_BC expr_value R_BC*/

import java.util.ArrayList;

public class Parser {

    public int iterator = 0;
    public ArrayList<Token> tokens = new ArrayList<Token>();
    public int len;
    public Token curToken;
    public int curLine = 0;

    public Parser(ArrayList<Token> tokens, int len) {
        this.tokens = tokens;
        this.len = len;
        curToken = tokens.get(iterator);
    }

    public void VAR() throws ParserException {
        if (curToken.getType() != "VAR")
            throw new ParserException(curLine, iterator, curToken, "VAR");
    }

    public void DIGIT() throws ParserException {
        if (curToken.getType() != "DIGIT")
            throw new ParserException(curLine, iterator, curToken, "DIGIT");
    }

    public void ASSIGN_OP() throws ParserException {
        if (curToken.getType() != "ASSIGN_OP")
            throw new ParserException(curLine, iterator, curToken, "ASSIGN_OP");
    }

    public void OP() throws ParserException {
        if (curToken.getType() != "OP")
            throw new ParserException(curLine, iterator, curToken, "OP");
    }

    public void L_BC() throws ParserException {
        if (curToken.getType() != "L_BC")
            throw new ParserException(curLine, iterator, curToken, "L_BC");
    }

    public void R_BC() throws ParserException {
        if (curToken.getType() != "R_BC")
            throw new ParserException(curLine, iterator, curToken, "R_BC");
    }

    public void ENDL() throws ParserException {
        if (curToken.getType() != "ENDL")
            throw new ParserException(curLine, iterator, curToken, "ENDL");
    }

    public void lang() {
        for (int i = 0; i < len; i++) {
            expr();
            if (iterator != tokens.size() - 1) {
                curToken = tokens.get(++iterator);
                curLine++;
            }
        }
    }

    public void expr() {
        try {
            VAR();
        } catch (ParserException e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
        }

        try {
            curToken = tokens.get(++iterator);
            ASSIGN_OP();
        } catch (ParserException e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
        }

        try {
            curToken = tokens.get(++iterator);
            expr_value();
        } catch (ParserException e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
        }

        try {
            ENDL();
        } catch (ParserException e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
        }
    }

    public void expr_value() throws ParserException {
        if (curToken.getType() != "ENDL") {
            try {
                value();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }

            while (curToken.getType() != "ENDL") {
                try {
                    curToken = tokens.get(++iterator);
                    if (curToken.getType() == "ENDL" || curToken.getType() == "R_BC") {
                        break;
                    }
                    OP();
                    curToken = tokens.get(++iterator);
                    value();
                } catch (IndexOutOfBoundsException e) {
                } catch (ParserException e) {
                    e.getInfo(curLine, iterator, e.current, e.expected);
                }
            }
        } else {
            throw new ParserException(curLine, iterator, curToken, "VALUE");
        }
    }

    public void value() throws ParserException {
        if (curToken.getType() != "VAR") {
            if (curToken.getType() != "DIGIT") {
                curToken = tokens.get(++iterator);
                if (curToken.getType() == "L_BC") {
                    infinity();
                }
            }
        }
    }

    public void infinity() {
        if (curToken.getType() != "ENDL") {
            try {
                L_BC();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }

            try {
                curToken = tokens.get(++iterator);
                expr_value();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }

            try {
                R_BC();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }
        }
    }
}
