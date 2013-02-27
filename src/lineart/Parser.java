package lineart;

import static lineart.Token.Type.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    
    private final Lexer lexer;
    Token next; // the next token to parse
    
    public Parser(Lexer lexer) {
        this.lexer = lexer;
    }
    
    public LineArt parse() throws IOException {
        next = lexer.next();
        return parse(new LineArt());
    }
    
    // parse remainder of file and add to "art"
    private LineArt parse(LineArt art) throws IOException {
        switch (next.type) {
        case END_OF_FILE:
            return art;
        case OPEN_PAREN:
            return parse(parseComponent(art));
        default:
            throw new IllegalStateException("Expected OPEN_PAREN, got " + next);
        }
    }
    
    // parse a component and return "art" with lines added
    private LineArt parseComponent(LineArt art) throws IOException {
        Pair<Integer, Integer> p1 = parsePoint();
        parseArrow();
        return parseMorePoints(art, p1);
    }
    
    // parse subsequent points and add lines to "art"
    private LineArt parseMorePoints(LineArt art, Pair<Integer, Integer> p1) throws IOException {
        Pair<Integer, Integer> p2 = parsePoint();
        art = art.addLine(p1.first, p1.second, p2.first, p2.second);
        switch (next.type) {
        case ARROW:
            parseArrow();
            return parseMorePoints(art, p2);
        default:
            return art;
        }
    }
    
    // parse a point and return it
    private Pair<Integer, Integer> parsePoint() throws IOException {
        List<Token> tokens = new ArrayList<Token>();
        Token.Type[] expected = new Token.Type[] {
                OPEN_PAREN, NUMBER, COMMA, NUMBER, CLOSE_PAREN
        };
        for (Token.Type expect : expected) {
            if (next.type != expect) {
                throw new IllegalStateException("Expected " + expect + ", got " + next);
            }
            tokens.add(next);
            next = lexer.next();
        }
        int x = Integer.parseInt(tokens.get(1).text);
        int y = Integer.parseInt(tokens.get(3).text);
        return new Pair<Integer, Integer>(x, y);
    }
    
    // parse an arrow
    private void parseArrow() throws IOException {
        if (next.type != ARROW) {
            throw new IllegalStateException("Expected ARROW, got " + next);
        }
        next = lexer.next();
    }
}
