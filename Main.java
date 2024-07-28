import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

            public class Main {
                public static void main(String[] args) {
                    LinearProbing<String> s = new LinearProbing<>(354);
                    createHashTable(s, "test2.txt");
                    GraphMatrix graph = GraphMatrix.readGraphfromFile("test2.txt");
                    graph.s = s;
                    Scanner input = new Scanner(System.in);

                    String choose = "1-Is There A Path\n" +
                            "2-All Paths Shorter Than EqualTo\n" +
                            "3-Shortest Path Length From To\n" +
                            "4-No Of Paths From To\n" +
                            "5-DFS from To\n" +
                            "6-BFS from To\n" +
                            "7-No Of Vertices In Component\n" +
                            "8-Quit";
                    int x = 0;
                    while (x != 8) {
                        System.out.println(choose);
                        x = input.nextInt();
                        switch (x) {
                            case 1:
                                input.nextLine();
                                System.out.println("Enter a Name1");
                                String Name1 = input.nextLine();
                                System.out.println("Enter a Name2");
                                String Name2 = input.nextLine();
                                System.out.println(graph.isThereAPath(Name1, Name2));
                                break;
                            case 2:
                                System.out.println("Enter  PathLen");
                                int pathLen = input.nextInt();
                                System.out.println("Enter  Vertex No ");
                                int VertexNo = input.nextInt();
                                System.out.println("Enter Name ");
                                input.nextLine();
                                String name1 = input.nextLine();
                                graph.AllPathsShorterThanEqualTo(pathLen, VertexNo, name1);
                                break;
                            case 3:
                                input.nextLine();
                                System.out.println("Enter a Name1");
                                String name = input.nextLine();
                                System.out.println("Enter a Name2");
                                String name2 = input.nextLine();
                                int value=graph.ShortestPathLengthFromTo(name,name2);
                                if (value>0){
                                    System.out.println(graph.ShortestPathLengthFromTo(name, name2));
                                }
                                break;
                            case 4:
                                input.nextLine();
                                System.out.println("Enter a Name1");
                                String n = input.nextLine();
                                System.out.println("Enter a Name2");
                                String n2 = input.nextLine();
                                System.out.println(graph.NoOfPathsFromTo(n, n2));
                                break;
                            case 5:
                                input.nextLine();
                                System.out.println("Enter a Name1");
                                String n4 = input.nextLine();
                                System.out.println("Enter a Name2");
                                String n3 = input.nextLine();
                                graph.DFSFromTo(n4, n3);
                                break;
                            case 6:
                                input.nextLine();
                                System.out.println("Enter a Name1");
                                String n8 = input.nextLine();
                                System.out.println("Enter a Name2");
                                String n9 = input.nextLine();
                                graph.BFSFromTo(n8, n9);
                                break;
                            case 7:
                                input.nextLine();
                                System.out.println("Enter a Name1");
                                String n7 = input.nextLine();
                                System.out.println(graph.NoOfVerticesInComponent(n7));
                                break;
                            case 8:
                                System.exit(0);
                        }
                    }
                }
                private static void createHashTable(LinearProbing<String> s, String f) {
                    try (Scanner in = new Scanner(new BufferedReader(new FileReader("toyExample.txt")));
                         BufferedWriter out = new BufferedWriter(new FileWriter(f))) {
                        ArrayList<TwoInteger> numbers = new ArrayList<>();
                        ArrayList<Integer> weights = new ArrayList<>();
                        while (in.hasNextLine()) {
                            String[] array = in.nextLine().split(",");
                            s.insert(array[0]);
                            s.insert(array[1]);
                            weights.add(Integer.parseInt(array[2]));
                            numbers.add(new TwoInteger(s.getIndex(array[0]), s.getIndex(array[1])));
                        }
                        out.write(s.M + "\n");
                        out.write(numbers.size() + "\n");
                        for (int i = 0; i < numbers.size(); i++)
                            out.write(numbers.get(i).first + " " + numbers.get(i).second + " " + weights.get(i) + "\n");


                    } catch (FileNotFoundException e) {
                        System.out.println(e.toString());

                    } catch (IOException e) {
                        System.out.println(e.toString());

                    }
                }

                static class TwoInteger {
                    int first;
                    int second;

                    public TwoInteger(int first, int second) {
                        this.first = first;
                        this.second = second;
                    }
                }

            }
