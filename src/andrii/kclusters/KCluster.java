package andrii.kclusters;

import andrii.uniheap.Heap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

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

public class KCluster {

    private Heap<Edge> edges;
    private int numEdges;
    private int numVertices;

    public KCluster(int numEdges, int numVertices){
        this.numEdges = numEdges;
        this.numVertices = numVertices;
        this.edges = new Heap<>(numEdges);
    }

    public KCluster(){
        //this.size = size;
        this.edges = new Heap<>(1000000);
    }

    public void init(List<Edge> edgesOfTheGraph){
        for(Edge e : edgesOfTheGraph){
            edges.insert(e);
        }
        this.numEdges = edgesOfTheGraph.size();
    }

    public void init(String fileName){
        try {
            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            String[] tokens;

            line = bufferedReader.readLine();
            this.numVertices = Integer.parseInt(line);

            while ((line = bufferedReader.readLine()) != null) {

                tokens = line.split(" ");
                edges.insert(new Edge(
                                    Integer.parseInt(tokens[0])-1,  // count vertices from 0
                                    Integer.parseInt(tokens[1])-1,
                                    Integer.parseInt(tokens[2])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.numEdges = edges.size();
    }

    public int getMaxSpacing(int clusterCount){

        UnionFind uf = new UnionFind(numVertices);

        Edge currEdge;

        while(!edges.isEmpty()){
            currEdge = edges.pop();

            if(!uf.isConnected(currEdge.src, currEdge.dest)){
                if(uf.getCount() == clusterCount)
                    return currEdge.weight;

                int v1 = uf.find(currEdge.src);  //parent vertex for source
                int v2 = uf.find(currEdge.dest); //parent vertex for destination
                uf.union(v1, v2);
            }

        }

        return -1;
    }
}
