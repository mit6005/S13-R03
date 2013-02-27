package lineart;

public class Pair<X, Y> {
    
    public final X first;
    public final Y second;
    
    public Pair(X first, Y second) {
        this.first = first;
        this.second = second;
    }
    
    @Override public String toString() {
        return "Pair<" + first + "," + second + ">";
    }
}
