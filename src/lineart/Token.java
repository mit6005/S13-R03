package lineart;

public class Token {
    
    public static enum Type {
        NUMBER,
        COMMA,
        OPEN_PAREN,
        CLOSE_PAREN,
        ARROW,
        END_OF_FILE
    }
    
    public final Type type;
    public final String text;
    
    public Token(Type type) {
        this(type, "");
    }
    
    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
    }
    
    @Override public String toString() {
        return "Token<" + type + ":" + text + ">";
    }
}
