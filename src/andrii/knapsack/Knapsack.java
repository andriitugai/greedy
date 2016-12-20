package andrii.knapsack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andrii_Tugai on 12/20/2016.
 */
class Item {
    int value;
    int weight;

    public Item(int value, int weight){
        this.value = value;
        this.weight = weight;
    }
}
public class Knapsack {

    private Item[] items;
    private int[][] solutions;
    private int numItems;
    private int suckSize;

    public void init(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] tokens;

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            this.suckSize = Integer.parseInt(tokens[0]);
            this.numItems = Integer.parseInt(tokens[1]);

            this.items = new Item[numItems+1];
            this.solutions = new int[numItems+1][suckSize + 1];

            int idx = 1;

            while ((line = bufferedReader.readLine()) != null) {

                tokens = line.split(" ");
                items[idx] = new Item(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                idx++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(int suckSize, int[][] items){
        this.suckSize = suckSize;
        this.numItems = items.length;

        System.out.println("nItems = " + this.numItems);

        this.items = new Item[items.length+1];
        this.solutions = new int[numItems+1][suckSize + 1];

        for(int idx = 1;idx < this.items.length;idx++){
            System.out.println("\t"+items[idx-1][0]+"\t"+items[idx-1][1]);
            this.items[idx] = new Item(items[idx-1][0], items[idx-1][1]);
        }
    }

    public int proceed(){
        // Initialization
        for(int x = 0; x < this.suckSize+1; x++)
            solutions[0][x] = 0;

        for(int i = 1; i < this.numItems+1; i++){
            for(int x = 0; x < this.suckSize + 1; x ++){

                if(x < items[i].weight)
                    solutions[i][x] = 0;
                else
                    solutions[i][x] = Math.max(
                            solutions[i-1][x],
                            solutions[i-1][x-items[i].weight]+items[i].value);

//                System.out.print("\t"+solutions[i][x]);
            }
//            System.out.println();
        }

        return solutions[numItems][suckSize];
    }

    public static void main(String[] args) {

        int[][] items = {{3,4}, {2,3}, {4,2}, {4,3}};
        Knapsack ks = new Knapsack();
        ks.init("knapsack1.txt");
//        ks.init(6,items);

        System.out.println("Solution = "+ ks.proceed());

    }
}
