
import java.util.Comparator;
import java.util.LinkedList;

    public class BFS {

        boolean[] marked;
        int from;

        public BFS(GraphMatrix g, int from, int to) {
            marked = new boolean[g.numV];
            this.from = from;
            bfs(g, from, to);
        }

        public boolean hasPathTo(int w) {
            return marked[w];
        }


        public void bfs(GraphMatrix g, int source,int to) {
            marked[source] = true;
            LinkedList<Integer> a = g.getNeighbor(source);
            if (a.size() == 0) {
                return;
            }
            LinkedList<Integer> q = new LinkedList<Integer>();
            q.addLast(source);
            LinkedList<String> bfsStringValues=new LinkedList<>();
            bfsStringValues.add(g.s.getData(q.getLast()));
            while (!q.isEmpty()) {
                source = q.removeFirst();
                a = g.getNeighbor(source);
                int finalSource = source;
                class Sort implements Comparator<Integer>{
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        if (g.matrix[finalSource][o1]<g.matrix[finalSource][o2])
                            return -1;
                        if (o1.equals(o2))
                            return 0;
                        else
                            return 1;
                    }
                }
                a.sort(new Sort());
                for (int i = 0; i < a.size(); i++) {
                    int w = a.get(i);
                    if (!marked[w]) {
                        String name=g.s.getData(w);
                        bfsStringValues.add(name);
                        if (w==to){
                            System.out.println(bfsStringValues);
                            return;
                        }

                        q.addLast(w);
                        marked[w] = true;
                    }
                }
            }
            System.out.println(bfsStringValues);
        }
    }

