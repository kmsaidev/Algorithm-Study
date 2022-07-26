import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static int S, A, B, W, count;
    static ArrayList<Node>[] graph;
    static boolean[] visited;
    static int inf = 30000000;
    static int[] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        visited = new boolean[V + 1];
        dist = new int[V + 1];
        for(int i = 0; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = inf;
        }

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        dist[S] = 0;
        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            Node node = new Node(B, W);
            graph[A].add(node);
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.distance - o2.distance;
            }
        });

        pq.add(new Node(S, dist[S]));
        while(!pq.isEmpty()){
            Node n = pq.poll();
            if(!visited[n.n]) {
                visited[n.n] = true;
                count++;
                for(Node node : graph[n.n]){
                    int temp = node.distance + n.distance;
                    node.distance = Math.min(temp, dist[node.n]);
                    dist[node.n] = node.distance;
                    pq.add(node);
                }
            }
            if(count == V){
                pq.clear();
            }
        }

        for(int i = 1; i <= V; i++) {
            if(dist[i] == inf)
                System.out.println("INF");
            else
                System.out.println(dist[i]);
        }

    }
}

class Node{
    int n, distance;

    public Node(int n, int distance) {
        this.n = n;
        this.distance = distance;
    }
}
