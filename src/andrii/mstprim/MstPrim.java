package andrii.mstprim;

import andrii.schedule.Job;
import andrii.uniheap.Heap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by Andrii_Tugai on 12/8/2016.
 */
public class MstPrim {

	private HashMap<Integer, Integer>   reached     = new HashMap<>();
	private HashMap<Integer, Integer>   unReached   = new HashMap<>();
	private HashMap<Integer, ArrayList<Edge>> edges = new HashMap<>();

    private ArrayList<Edge> mst = new ArrayList<>();

	public void init(String fileName){
		try {
			File file = new File(fileName);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			String line;
			String[] tokens;
            int v1, v2, weight;

            // Skip the first line (number of vertices and edges)
            bufferedReader.readLine();

			while ((line = bufferedReader.readLine()) != null) {

				tokens = line.split(" ");
                v1 = Integer.parseInt(tokens[0]);
                v2 = Integer.parseInt(tokens[1]);
                weight = Integer.parseInt(tokens[2]);

                if(!unReached.containsKey(v1)) unReached.put(v1,v1);
                if(!unReached.containsKey(v2)) unReached.put(v2,v2);

                if(!edges.containsKey(v1)) edges.put(v1, new ArrayList<>());
                edges.get(v1).add(new Edge(v1, v2, weight));

                if(!edges.containsKey(v2)) edges.put(v2, new ArrayList<>());
                edges.get(v2).add(new Edge(v1, v2, weight));

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

        //**************************************************
        // Check of init() method's results
        System.out.println("Vertices, total (unreached):\t"+unReached.size());
        System.out.println("Vertices, total (edges) :\t\t"+edges.size() );

        int numEdges = 0;
        for (int i: edges.keySet())
            numEdges += edges.get(i).size();

        System.out.println("\nNumber of edges stored:\t\t\t" + numEdges);
        System.out.println("Number of edges in graph:\t\t" + numEdges/2);

        //**************************************************
	}

	public ArrayList<Edge> proceedMstPrime(){

        Heap<Edge> minHeap = new Heap<>(
                new Comparator<Edge>() {
                    @Override
                    public int compare(Edge o1, Edge o2) {
                        return o1.getWeight() - o2.getWeight();
                    }
                }
        );

        // get random vertice (for love to high art only :)
        int num2skip = (int)(Math.random()*unReached.size());

        Iterator<Integer> it = unReached.values().iterator();
        for(int i = 0; i < num2skip; i++) it.next();

        int nextV = it.next();

        while (true){

            unReached.remove(nextV);
            reached.put(nextV,nextV);

            if(unReached.isEmpty()) break;

            ArrayList<Edge> edgesFromV = edges.get(nextV);
            // insert all edges connected to nextV into minHeap
            for(Edge e : edgesFromV ) minHeap.insert(e);

            boolean isDone = false;
            while(!isDone){
                Edge minEdge = minHeap.pop();
                if( unReached.containsKey(minEdge.getV1()) && !unReached.containsKey(minEdge.getV2())){
                    isDone = true;
                    this.mst.add(minEdge);
                    nextV = minEdge.getV1();
                }
                if( unReached.containsKey(minEdge.getV2()) && !unReached.containsKey(minEdge.getV1())){
                    isDone = true;
                    this.mst.add(minEdge);
                    nextV = minEdge.getV2();
                }
            }
        }
        return mst;
    }

    public static void main(String[] args) {

        MstPrim mstPrim = new MstPrim();
        mstPrim.init("edges.txt");
        ArrayList<Edge> mst = mstPrim.proceedMstPrime();

        System.out.println("Number edges in MST: " + mst.size());
        int sum = 0;
        for(Edge e : mst) sum += e.getWeight();
        System.out.println("MST length:          " + sum);
    }
}
