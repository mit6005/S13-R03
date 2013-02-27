package lineart;

import java.io.*;

public class Main {
    
    public static void main(String[] args) throws IOException {
        // In this example, we'll read art from a file.
        Reader reader = new FileReader("art.txt");
        
        // The lexer consumes raw input and produces tokens...
        Lexer lexer = new Lexer(reader);
        
        // ... which the parser will consume...
        Parser parser = new Parser(lexer);
        
        // ... to produce an instance of our line art datatype.
        LineArt art = parser.parse();
        
        System.out.println("Behold my latest masterpiece:\n  " + art);
    }
}
