package lineart;

import java.io.*;

public class Lexer {
    
    private final PushbackReader reader;
    
    public Lexer(Reader reader) {
        this.reader = new PushbackReader(reader);
    }
    
    public Token next() throws IOException {
        int cc = reader.read();
        if (cc < 0) {
            return new Token(Token.Type.END_OF_FILE);
        }
        if (Character.isWhitespace(cc)) {
            return next(); // skip whitespace and keep looking
        }
        if (cc == '(') {
            return new Token(Token.Type.OPEN_PAREN);
        }
        if (cc == ')') {
            return new Token(Token.Type.CLOSE_PAREN);
        }
        if (cc == ',') {
            return new Token(Token.Type.COMMA);
        }
        if (cc == '-') {
            return lexDash();
        }
        if (Character.isDigit(cc)) {
            return new Token(Token.Type.NUMBER, lexNumber(cc));
        }
        throw new IllegalStateException("Unexpected '" + (char)cc + "'");
    }
    
    private Token lexDash() throws IOException {
        int cc = reader.read();
        if (cc < 0) {
            throw new IllegalStateException("Unexpected EOF after dash");
        }
        if (cc == '>') {
            return new Token(Token.Type.ARROW);
        }
        if (Character.isDigit(cc)) {
            return new Token(Token.Type.NUMBER, "-" + lexNumber(cc));
        }
        throw new IllegalStateException("Unexpected '" + (char)cc + "' after dash");
    }
    
    private String lexNumber(int cc) throws IOException {
        if (cc < 0) { throw new IllegalStateException("Unexpected EOF in number"); }
        if (Character.isDigit(cc)) {
            return (char)cc + lexNumber(reader.read());
        } else {
            reader.unread(cc); // another token will want this character
            return "";
        }
    }
}
