package andrii.mstprim;

/**
 * Created by Andrii_Tugai on 12/8/2016.
 */
public class Edge implements Comparable<Edge> {

    private int v1;
    private int v2;
    private int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public int getV1() {
        return v1;
    }

    public int getV2() {
        return v2;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o2) {
        return this.weight - o2.getWeight();
    }

    @Override
    public  String toString(){
        return "v1: "+ v1 +";\tv2: "+v2 +"\tweight: "+weight;
    }
}
