package andrii.huffman;

import andrii.uniheap.Heap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Andrii_Tugai on 12/14/2016.
 */

public class Huffman {

    private Heap<Symbol> symbols = new Heap<>();
    private HashMap<Integer, Symbol> alphabet = new HashMap<>();

    public void init(String fileName){
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;

            line = bufferedReader.readLine();
            Symbol sym;
            int num = 0;

            while ((line = bufferedReader.readLine()) != null) {
                sym = new Symbol(Integer.parseInt(line));
                num++;

                symbols.insert(sym);
                alphabet.put(num, sym);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(int[] weights){
//        this.numSymbols = weights.length;
        Symbol sym;

        for(int i = 0; i< weights.length; i++){
            sym = new Symbol(weights[i]);
            symbols.insert(sym);
            alphabet.put(i,sym);
        }
    }

    public void proceed(){
        Symbol rS, lS;

        while (true){
            rS = symbols.pop();
            rS.addCodePrefix("0");

            lS = symbols.pop();
            lS.addCodePrefix("1");

            if(symbols.isEmpty()) break;

            symbols.insert(new Symbol(rS.weight+lS.weight, rS, lS));
        }
    }

    public int getMaxCodeLength() {
        int maxCodeLength = 0;
        for(Symbol sym : alphabet.values()){
            if(sym.code.length() > maxCodeLength)
                maxCodeLength = sym.code.length();
        }
        return maxCodeLength;
    }

    public int getMinCodeLength() {
        int minCodeLength = Integer.MAX_VALUE;
        for(Symbol sym : alphabet.values()){
            if(sym.code.length() < minCodeLength)
                minCodeLength = sym.code.length();
        }
        return minCodeLength;
    }
}
