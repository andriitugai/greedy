package andrii.knapsack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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

    @Override
    public String toString(){
        return String.format("(%10d,%10d)", this.value, this.weight);
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

        this.items = new Item[items.length+1];
        this.solutions = new int[numItems+1][suckSize + 1];

        for(int idx = 1;idx < this.items.length;idx++){
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

    public ArrayList<Item> restore(){
        ArrayList<Item> bag = new ArrayList<>();

        int curSize = suckSize;
        int curItem = numItems;

        int curValue = solutions[curItem][curSize];

        while(curValue > 0){
            if(curValue == solutions[curItem-1][curSize]){  // curItem isn't included in bag
                curItem --;
            } else {                                        // curItem is included in bag
                bag.add(items[curItem]);
                curValue    -= items[curItem].value;
                curSize     -= items[curItem].weight;
                curItem     -= 1;
                if(solutions[curItem][curSize] != curValue)
                    System.out.println("Alert! Item #"+curItem);
            }
        }
        return bag;
    }

    public static void main(String[] args) {

        int[][] items = {{3,4}, {2,3}, {4,2}, {4,3}};
        Knapsack ks = new Knapsack();
        ks.init("knapsack1.txt");
//        ks.init(6,items);

        System.out.println("Number of items : " + ks.numItems);
        System.out.println("Size of knapsack: " + ks.suckSize);

//        System.out.println("\n-----------------------------");
//        System.out.println(ks.items[1]);
//        System.out.println(ks.items[2]);
//        System.out.println(ks.items[3]);
        System.out.println("\n-----------------------------");
        System.out.println("Solution        : "+ ks.proceed());

        ArrayList<Item> bag = ks.restore();
        int totalWeight = 0;
        int totalValue = 0;
        System.out.println("Knapsack contains:");
        int idx = 0;
        for (Item item: bag){
            System.out.printf("%3d. %s\n",++idx, item);
            totalValue += item.value;
            totalWeight += item.weight;
        }
        System.out.println("-----------------------------");
        System.out.println("Total weight = " + totalWeight);
        System.out.println("Total value  = " + totalValue);

    }
}
