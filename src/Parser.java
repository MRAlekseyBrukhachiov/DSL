/*
VAR -> "[a-z]\\w*"
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
infinity -> L_BC expr_value R_BC
*/

import java.util.ArrayList;

public class Parser {

    public int iterator = 0;
    public ArrayList<Token> tokens;
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

    public void lang() throws ParserException {
        for (int i = 0; i < len; i++) {
            curLine++;
            expr();
        }
    }

    public void expr() throws ParserException, IndexOutOfBoundsException {
        try {
            VAR();
        } catch (ParserException e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
        }
        curToken = tokens.get(++iterator);
        try {
            ASSIGN_OP();
        } catch (ParserException e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
        }
        curToken = tokens.get(++iterator);
        while (curToken.getType() != "ENDL") {
            expr_value();
            curToken = tokens.get(++iterator);
        }
        try {
            ENDL();
        } catch (ParserException e) {
            e.getInfo(curLine, iterator, e.current, e.expected);
        }
        curToken = tokens.get(++iterator);
    }

    public void expr_value() throws ParserException {
        if ((curToken.getType() == "VAR") || (curToken.getType() == "DIGIT")) {
            value();
            curToken = tokens.get(++iterator);
        }
        else if (curToken.getType() == "L_BC") {
            infinity();
            curToken = tokens.get(++iterator);
        }
        if (curToken.getType() == "OP") {
            try {
                OP();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }
            curToken = tokens.get(++iterator);
            try {
                value();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }
        }
    }

    public void value() throws ParserException{
        if (curToken.getType() == "VAR")
            VAR();
        else if (curToken.getType() == "DIGIT") {
            try {
                DIGIT();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }
        } else {
            infinity();
        }
    }

    public void infinity() {
        if (curToken.getType() != "ENDL") {
            try {
                L_BC();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }
            curToken = tokens.get(++iterator);
            try {
                expr_value();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }
            curToken = tokens.get(++iterator);
            try {
                R_BC();
            } catch (ParserException e) {
                e.getInfo(curLine, iterator, e.current, e.expected);
            }
        }
    }
}
