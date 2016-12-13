package andrii.mstprim;

/**
 * Created by Andrii_Tugai on 12/8/2016.
 */
public class Vertice {
    private int id;
    private boolean visited;

    public Vertice(int id) {
        this.id = id;
        this.visited = false;
    }

    public int getId() {
        return id;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
