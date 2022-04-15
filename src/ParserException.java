public class ParserException extends Exception {

    public Token current;
    public String expected;
    public int numLine;
    public int numToken;

    public ParserException(int numLine, int numToken, Token current, String expected) {
        this.numLine = numLine;
        this.numToken = numToken;
        this.current = current;
        this.expected = expected;
    }

    public void getInfo(int numLine, int numToken, Token current, String expected) {
        System.out.println("Line: " + numLine + " Token: " + (numToken + 1) + " - Expected: " + expected + " but received: " + current.getType());
    }
}
