package Algorithms;

import java.util.*;

class StronglyConnectedComponents {
    int V;  //number of nodes
    ArrayList<Integer> adj[];   //adjacency list
    int time;

    StronglyConnectedComponents(int v) {
        this.V = v;
        this.time = 0;
        this.adj = new ArrayList[v];
        //adj list of v elements(arrayLists)
        for(int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }


    void util(int u, int low[], int disc[], int stackMember[], Stack<Integer> st) {
        stackMember[u] = 1; //visited
        disc[u] = time;  //first time visiting time
        low[u] = time++;  //after update time +1
        st.push(u);  //dfs - take last added as first observal vertex

        for (Integer i : adj[u]) { //traversing adjacents of u
            if (disc[i] == -1) {
                util(i, low, disc, stackMember, st);
                if (low[u] > low[i]) low[u] = low[i];  //update If node v is not visited
            } else if (stackMember[i] != 0)  //if i is still in stack update value
            {
                if (low[u] > disc[i]) low[u] = disc[i];
            }
        }

        int w = -1;
        if (low[u] == disc[u]) {
            while (w != u) //while there is a path
            {

                w = (int) st.pop();
                stackMember[w] = 0;
                System.out.print(w + " ");

            }
            System.out.println();
        }


    }

    void SCC() {
        //first time all vertices has no parent, therefore fill with -1
        int disc[] = new int[V];
        Arrays.fill(disc, -1);
        int low[] = new int[V];
        Arrays.fill(low, -1);

        int stackMember[] = new int[V];
        Stack<Integer> st = new Stack<Integer>();
        //all vertices not visited filling
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)//if not visited vertex
                util(i, low, disc, stackMember, st);
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of nodes");
        int n = sc.nextInt();
        System.out.println("Enter the number of edge");
        int m = sc.nextInt();
        m++;
        StronglyConnectedComponents g = new StronglyConnectedComponents(n);
        System.out.println("Enter the edges:");

        while (m-- > 0 && sc.hasNext()) {
            String[] input = sc.nextLine().split(" ");
            if (input.length == 2) {
                g.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
            }
        }
        g.SCC();

    }
}