import java.util.LinkedList;
public class DFS {
    boolean[] marked;
    int from;
    public DFS(GraphMatrix g, int from,int to) {
        marked = new boolean[g.numV];
        this.from = from;
        LinkedList<String> dfsValues=new LinkedList<>();
        boolean[] isValid=new boolean[1];
        dfs(g, from, to, dfsValues, isValid);
        if (!isValid[0])
            System.out.println("no path");
    }
    public void dfs(GraphMatrix g, int source, int to, LinkedList<String> dfsValues, boolean[] isValid) {
        marked[source] = true;
        String name=g.s.getData(source);
        dfsValues.add(name);
        if (source==to){
            System.out.println(dfsValues);
            isValid[0]=true;
            return;
        }
        LinkedList<Integer> neighbors=g.getNeighbor(source);
        for (int i = 0; i < neighbors.size(); i++) {
            int neighbor = neighbors.get(i);
            if (!marked[neighbor]) {
                dfs(g, neighbor, to, dfsValues, isValid);
            }
        }
    }
}



