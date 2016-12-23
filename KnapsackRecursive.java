package andrii.knapsack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by andrii on 23.12.16.
 */
class MarkedItem{
    int weight;
    int value;
    boolean marked = false;

    public MarkedItem(int value, int weight) {
        this.weight = weight;
        this.value = value;
        this.marked = false;
    }
}
public class KnapsackRecursive {

    private MarkedItem[] items;
    private int capacity;
    private int numItems;

    public void init(String fileName) {
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] tokens;

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            this.capacity = Integer.parseInt(tokens[0]);
            this.numItems = Integer.parseInt(tokens[1]);

            this.items = new MarkedItem[numItems];

            int idx = 0;

            while ((line = bufferedReader.readLine()) != null) {

                tokens = line.split(" ");
                items[idx] = new MarkedItem(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]));
                idx++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init(int capacity, int[][] items){
        this.capacity = capacity;
        this.numItems = items.length;

        this.items = new MarkedItem[items.length];

        for(int idx = 0;idx < this.items.length;idx++){
            this.items[idx] = new MarkedItem(items[idx][0], items[idx][1]);
        }
    }

    // Returns the maximum value that can be put in a knapsack of capacity W
    int knapSack(int W, int wt[], int val[], int n)
    {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // If weight of the nth item is more than Knapsack capacity W, then
        // this item cannot be included in the optimal solution
        if (items[n-1].weight > W)
            return knapSack(W, wt, val, n-1);

            // Return the maximum of two cases: (1) nth item included (2) not included
        else {
            if()
        }return max( items[n-1].value + knapSack(W-items[n-1].weight, wt, val, n-1),
                knapSack(W, wt, val, n-1)
        );
    }

}
