package andrii.huffman;

/**
 * Created by Andrii_Tugai on 12/14/2016.
 */
public class HuffmanDriver {

    public static void main(String[] args) {

        int[] a = {320, 250, 200, 180, 50};

        Huffman huffman = new Huffman();
        huffman.init("huffman.txt");
//        huffman.init(a);
        huffman.proceed();

        System.out.println("Max Huffman's code length: " + huffman.getMaxCodeLength());
        System.out.println("Min Huffman's code length: " + huffman.getMinCodeLength());
    }
}
