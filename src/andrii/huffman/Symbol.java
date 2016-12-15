package andrii.huffman;

/**
 * Created by Andrii_Tugai on 12/15/2016.
 */
public class Symbol implements Comparable<Symbol>{
    int weight;
    String code = "";
    Symbol rSymbol = null;
    Symbol lSymbol = null;

    Symbol(int weight){
        this.weight = weight;
        this.rSymbol = null;
        this.lSymbol = null;
    }

    // Updates code - adds
    void addCodePrefix (String prefix){
        if(this.rSymbol == null && this.lSymbol == null){
            this.code = new StringBuilder(prefix).append(this.code).toString();
        } else {
            if(this.rSymbol != null) {
                rSymbol.addCodePrefix(prefix);
            }
            if(this.lSymbol != null) {
                lSymbol.addCodePrefix(prefix);
            }
        }
    }

    public Symbol(int weight, Symbol rSymbol, Symbol lSymbol) {
        this.weight = weight;
        this.rSymbol = rSymbol;
        this.lSymbol = lSymbol;
    }

    @Override
    public int compareTo(Symbol another) {
        return this.weight - another.weight;
    }
}

