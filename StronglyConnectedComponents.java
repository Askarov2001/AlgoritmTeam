
import java.util.*;

class StronglyConnectedComponents {
    int V;  
    ArrayList<Integer> adj[];   
    int time;

    StronglyConnectedComponents(int v) {
        this.V = v;
        this.time = 0;
        this.adj = new ArrayList[v];
       
        for(int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }


    void util(int u, int low[], int disc[], int stackMember[], Stack<Integer> st) {
        stackMember[u] = 1; 
        disc[u] = time; 
        low[u] = time++;  
        st.push(u); 

        for (Integer i : adj[u]) { 
            if (disc[i] == -1) {
                util(i, low, disc, stackMember, st);
                if (low[u] > low[i]) low[u] = low[i];  
            } else if (stackMember[i] != 0)  
            {
                if (low[u] > disc[i]) low[u] = disc[i];
            }
        }

        int w = -1;
        if (low[u] == disc[u]) {
            while (w != u) 
            {

                w = (int) st.pop();
                stackMember[w] = 0;
                System.out.print(w + " ");

            }
            System.out.println();
        }


    }

    void SCC() {
       
        int disc[] = new int[V];
        Arrays.fill(disc, -1);
        int low[] = new int[V];
        Arrays.fill(low, -1);

        int stackMember[] = new int[V];
        Stack<Integer> st = new Stack<Integer>();
       
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1)
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