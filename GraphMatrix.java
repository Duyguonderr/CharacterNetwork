import java.io.*;
import java.util.*;


class GraphMatrix {

        int matrix[][];
        int numV;
        int numE;
       LinearProbing<String> s;


        public GraphMatrix(int V) {
            this.numV = V;
            matrix = new int[V][V];
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        public void addEdge(int from, int to, int weight) {
            matrix[from][to] = weight;
            matrix[to][from]= weight;
            numE++;
        }

    public LinkedList<Integer> getNeighbor(int vertices){  //used for traversing the graph and for other graph algorithms.
        LinkedList<Integer> temp=new LinkedList<>();
        for (int i=0;i<matrix.length;i++)
            if (!(matrix[vertices][i]==0 && matrix[i][vertices]==0)){
                temp.add(i);
            }
        return temp;
    }

    public int degree(int v) {
            int degree = 0;
            for (int i = 0; i < numV; i++) {
                degree += matrix[v][i];
            }
            return degree;
        }

        public static int findMaxDegree(GraphMatrix g){
            int degree = 0;
            int maxDegreeVertex = 0;
            for (int i = 0; i < g.numV; i++) {
                System.out.println("Degree: "+g.degree(i));
                if(g.degree(i)>degree){
                    degree = g.degree(i);
                    maxDegreeVertex = i;
                }
            }
            return maxDegreeVertex;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder("");
            for (int i = 0; i < numV; i++) {
                for (int j = 0; j < numV; j++) {
                    s.append(matrix[i][j] + " ");
                }
                s.append("\n");
            }
            return s.toString();
        }
        public static GraphMatrix readGraphfromFile(String f) {
            try(Scanner in=new Scanner(new BufferedReader(new FileReader("test2.txt")))){
                int v=in.nextInt();
                int e=in.nextInt();
                GraphMatrix graph=new GraphMatrix(v);
                for (int i=0;i<e;i++)
                    graph.addEdge(in.nextInt(),in.nextInt(), in.nextInt());
                return graph;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean isThereAPath(String name1, String name2){
            int name1Index=s.getIndex(name1);
            int name2Index=s.getIndex(name2);
            boolean[] isVisited=new boolean[numV];
            ArrayList<Integer> pathList = new ArrayList<>();
            List<List<Integer>> allPaths=new ArrayList<>();
            pathList.add(name1Index);
            printAllPaths(name1Index,name2Index,isVisited,pathList,allPaths);
            return !allPaths.isEmpty();

        }
    public void AllPathsShorterThanEqualTo (int pathLen, int VertexNo, String name1){
        int nameIndex=s.getIndex(name1);
        boolean[] isVisited=new boolean[numV];
        ArrayList<Integer> pathList = new ArrayList<>();
        List<List<Integer>> allPaths=new ArrayList<>();
        pathList.add(nameIndex);
        for (int i=0;i<s.M;i++){
            if (nameIndex!=i){
                printAllPaths(nameIndex,i,isVisited,pathList,allPaths);
                Arrays.fill(isVisited,false);
            }
        }
        for (int i=0;i<allPaths.size();i++)
            if (allPaths.get(i).size()<VertexNo)
                allPaths.remove(allPaths.get(i));
        for (int i=0;i<allPaths.size();i++) {
            int sumOfWeight=0;
            for (int j = 0; j < allPaths.get(i).size(); j++) {
                if (j==allPaths.get(i).size()-1)
                    break;
                sumOfWeight+=matrix[allPaths.get(i).get(j)][allPaths.get(i).get(j+1)];
            }
            if (sumOfWeight>pathLen)
                allPaths.remove(allPaths.get(i));
        }
        for (int i=0;i<allPaths.size();i++) {
            ArrayList<String> tempArray=new ArrayList<>();
            for (int j=0;j<allPaths.get(i).size();j++){
                tempArray.add(s.getData(allPaths.get(i).get(j)));
            }
            System.out.println(tempArray);
        }
    }
        public void DFSFromTo(String name1, String name2){
            int name1Index=s.getIndex(name1);
            int name2Index=s.getIndex(name2);
            new DFS(this,name1Index,name2Index);
        }

        public void BFSFromTo(String name1, String name2){
            int name1Index=s.getIndex(name1);
            int name2Index=s.getIndex(name2);
            new BFS(this,name1Index,name2Index);
        }

    public int ShortestPathLengthFromTo(String name1, String name2){
        int name1Index=s.getIndex(name1);
        int name2Index=s.getIndex(name2);
        boolean[] isVisited=new boolean[numV];
        ArrayList<Integer> pathList = new ArrayList<>();
        List<List<Integer>> allPaths=new ArrayList<>();
        pathList.add(name1Index);
        printAllPaths(name1Index,name2Index,isVisited,pathList,allPaths);
        if (allPaths.isEmpty()) {
            System.out.println("infinity");
            return -1;
        }
        ArrayList<Integer> allWeights=new ArrayList<>();
        int high;
        int low=0;
        for (int i=0;i<allPaths.size();i++) {
            high=0;
            for (int j = 0; j < allPaths.get(i).size(); j++) {
                if (j==allPaths.get(i).size()-1)
                    break;
                high+=matrix[allPaths.get(i).get(j)][allPaths.get(i).get(j+1)];
            }
            if (i==0)
                low=high;

            if (high<low)
                low=high;
        }
        return low;

    }
    public int NoOfPathsFromTo(String name1, String name2){
        int name1Index=s.getIndex(name1);
        int name2Index=s.getIndex(name2);
        boolean[] isVisited=new boolean[numV];
        ArrayList<Integer> pathList = new ArrayList<>();
        List<List<Integer>> allPaths=new ArrayList<>();
        pathList.add(name1Index);
        printAllPaths(name1Index,name2Index,isVisited,pathList,allPaths);
        return allPaths.size();

    }
    public int NoOfVerticesInComponent(String name1){
        int nameIndex=s.getIndex(name1);
        boolean[] isVisited=new boolean[numV];
        ArrayList<Integer> pathList = new ArrayList<>();
        List<List<Integer>> allPaths=new ArrayList<>();
        pathList.add(nameIndex);
        for (int i=0;i<s.M;i++){
            if (nameIndex!=i){
                printAllPaths(nameIndex,i,isVisited,pathList,allPaths);
                Arrays.fill(isVisited,false);
            }
        }
        ArrayList<Integer> amountOfVerticesInComponent=new ArrayList<>();
        for (int i=0;i<allPaths.size();i++) {
            for (int j = 0; j < allPaths.get(i).size(); j++) {
                if (!amountOfVerticesInComponent.contains(allPaths.get(i).get(j))){
                    amountOfVerticesInComponent.add(allPaths.get(i).get(j));
                }
            }
        }
        return amountOfVerticesInComponent.size();

    }

    private void printAllPaths(Integer visited, Integer dest, boolean[] isVisited, List<Integer> localPathList, List<List<Integer>> allPaths) {
        if (visited.equals(dest)) {
            allPaths.add(new ArrayList<>(localPathList));
            return;
        }
        isVisited[visited] = true;
        for (Integer i : getNeighbor(visited)) {
            if (!isVisited[i]) {
                localPathList.add(i);
                printAllPaths(i, dest, isVisited, localPathList,allPaths);
                localPathList.remove(i);
            }
        }
        isVisited[visited] = false;
    }
}




