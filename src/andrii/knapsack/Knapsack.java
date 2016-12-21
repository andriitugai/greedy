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

//        System.out.println("nItems = " + this.numItems);

        this.items = new Item[items.length+1];
        this.solutions = new int[numItems+1][suckSize + 1];

        for(int idx = 1;idx < this.items.length;idx++){
//            System.out.println("\t"+items[idx-1][0]+"\t"+items[idx-1][1]);
            this.items[idx] = new Item(items[idx-1][0], items[idx-1][1]);
        }
    }

    public int proceed(){
        // Initialization
        //What if the knapsack's capacity is 0 - Set all rows at column 0 to be 0
        for(int x = 0; x < this.suckSize+1; x++)
            solutions[0][x] = 0;

        //??What if there are no items at home.  Fill the first row with 0

        for(int i = 1; i < this.numItems+1; i++){
            for(int x = 0; x < this.suckSize + 1; x ++){

                if(x < items[i].weight)
                    //If the current item's weight is more than the running weight, just carry forward the value without the current item
                    solutions[i][x] = solutions[i-1][x];
                else
                    solutions[i][x] = Math.max(
                            solutions[i-1][x],
                            solutions[i-1][x-items[i].weight]+items[i].value
                    );
            }
        }

        return solutions[numItems][suckSize];
    }

    public static void main(String[] args) {

        int[][] items = {{3,4}, {2,3}, {4,2}, {4,3}};
        Knapsack ks = new Knapsack();
        ks.init("knapsack1.txt");
//        ks.init(6,items);

        System.out.println("Number of items : " + ks.numItems);
        System.out.println("Size of knapsack: " + ks.suckSize);
//        System.out.println("\n-----------------------------");
//        System.out.println("Item #1         : value - "+ ks.items[1].value+", weight - "+ks.items[1].weight);
//        System.out.println("Item #2         : value - "+ ks.items[2].value+", weight - "+ks.items[2].weight);
//        System.out.println("Item #3         : value - "+ ks.items[3].value+", weight - "+ks.items[3].weight);
//        System.out.println("Item #98         : value - "+ ks.items[98].value+", weight - "+ks.items[98].weight);
//        System.out.println("Item #99         : value - "+ ks.items[99].value+", weight - "+ks.items[99].weight);
//        System.out.println("Item #100        : value - "+ ks.items[100].value+", weight - "+ks.items[100].weight);

        System.out.println("\n-----------------------------");
        System.out.println("Solution        : "+ ks.proceed());

    }
}
