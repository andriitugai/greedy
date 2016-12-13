package andrii.kclusters;

/**
 * Created by Andrii_Tugai on 12/12/2016.
 */


/**
 * Union-Find DS node
 */
class UFNode {
    int parent; // parent of Vertex at i in the nodeHolder
    int rank; // Number of object present in the tree/ Cluster

    UFNode(int parent, int rank) {
        this.parent = parent;
        this.rank = rank;
    }
}
public class UnionFind {
    private UFNode[] ufNodes;
    int numVertices;

    private int count;

    public UnionFind(int size){
        this.count = size;
        this.numVertices = size;
        ufNodes = new UFNode[size];
        for(int i = 0; i < size; i++){
            ufNodes[i] = new UFNode(i,1);
        }
    }

    // returns the parent of the given vertex
    public int find(int vertex){

        if(vertex < 0 || vertex > ufNodes.length-1) {
            System.out.println("Vertex = " + vertex);
            throw new ArrayIndexOutOfBoundsException("Illegal number of vertex in the UnionFind structure");
        }
        if(ufNodes[vertex].parent != vertex)
            ufNodes[vertex].parent = find(ufNodes[vertex].parent);

        return ufNodes[vertex].parent;
    }

    public int getCount(){
        return this.count;
    }

    public boolean isConnected(int v1, int v2){
        return find(v1) == find(v2);
    }

    public void union(int v1, int v2) {
        int i = find(v1);
        int j = find(v2);

        if (i == j)
            return;

        if (ufNodes[i].rank < ufNodes[j].rank) {
            ufNodes[i].parent = j;
            ufNodes[j].rank = ufNodes[j].rank + ufNodes[i].rank;
        } else {
            ufNodes[j].parent = i;
            ufNodes[i].rank = ufNodes[i].rank + ufNodes[j].rank;
        }
        count--;
    }
}
