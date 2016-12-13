package andrii.kclusterbig;

import andrii.kclusters.UnionFind;
import andrii.uniheap.Heap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Andrii_Tugai on 12/12/2016.
 */

 class Edge implements Comparable<Edge>{
    int src;
    int dest;
    int weight;

    public Edge(int src,int dest,int weight) {
        this.src=src;
        this.dest=dest;
        this.weight=weight;
    }

    @Override
    public String toString() {
        return "Edge: "+src+ " - " + dest + " | " +"  Weight: "+ weight;
    }

    @Override
    public int compareTo(Edge another) {
        return this.weight - another.weight;
    }
}

public class BigClusterDriver {

    private int[][] inputMatrix;
    private Heap<Edge> edges = new Heap<>(1000000);
    private int numVertices;
    private int numBits;

    public static void main(String[] args) {

        BigClusterDriver bc = new BigClusterDriver();
        bc.init("clustering_big.txt");

        bc.filterInput(3);

        UnionFind uf = new UnionFind(bc.numVertices);

        Edge currEdge;

        while(!bc.edges.isEmpty()){
            currEdge = bc.edges.pop();

            if(!uf.isConnected(currEdge.src, currEdge.dest)){

                int v1 = uf.find(currEdge.src);  //parent vertex for source
                int v2 = uf.find(currEdge.dest); //parent vertex for destination
                uf.union(v1, v2);
            }
        }

        System.out.println("Number of clusters: " + uf.getCount());
    }

    // filters edges longer than [limit]
    // Hamming distance
    public void filterInput(int limit){

        String[] tok1, tok2;
        int distance;

        for(int index = 0; index < this.numVertices; index++){
            for(int j = index + 1; j < this.numVertices; j++){
                distance = 0;
                int i = 0;
                while(distance < limit && i < this.numBits){
                    if(this.inputMatrix[index][i] != this.inputMatrix[j][i])
                        distance++;

                    i++;
                }
                if(distance < limit) edges.insert(new Edge(index, j, distance));
            }
            if(index%1000 == 0){
                System.out.printf("Proceeded: %6d; Selected: %10d\n", index, edges.size());

            }
        }

    }

    public void init(String fileName){
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] tokens;

            line = bufferedReader.readLine();
            tokens = line.split(" ");
            this.numVertices = Integer.parseInt(tokens[0]);
            this.numBits = Integer.parseInt(tokens[1]);
            this.inputMatrix = new int[numVertices][numBits];

            int index = 0;

            while ((line = bufferedReader.readLine()) != null) {
                tokens = line.split(" ");
                for(int i=0; i < this.numBits; i++)
                    this.inputMatrix[index][i] = Integer.parseInt(tokens[i]);
                index++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
