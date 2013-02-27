package lineart;

/**
 * Immutable line art.
 * Constructors: new LineArt()
 * Producers: addLine(...)
 * Observers: toString()
 */
public class LineArt {
    
    // Why are you looking at my implementation? Go away.
    
    private class Segment extends LineArt {
        
        // LineArt.this is my implicit "rest". Too clever by half.
        
        final int x1, y1, x2, y2;
        
        Segment(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
        
        @Override public String toString() {
            return "(" + x1 + "," + y1 +
                   ")->(" +x2 + "," + y2 + ") " + LineArt.this.toString();
        }
    }
    
    public LineArt() {
        // I represent the empty art.
    }
    
    public LineArt addLine(int x1, int y1, int x2, int y2) {
        return new Segment(x1, y1, x2, y2);
    }
    
    @Override public String toString() {
        return "<end>";
    }
}
