import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int A, B, T;
    static ArrayList<Node>[] graph;
    static int[] dist, previous;
    static boolean[] visited;
    static int inf = 1000000000;
    static int maxDiff = 0;
    static boolean isFirst = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        previous = new int[N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            dist[i] = inf;
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(0, B, T));
            graph[B].add(new Node(0, A, T));
        }
        dijkstra(new Node(0, 1, 0));

        isFirst = false;
        int minRoute = dist[N];
        deleteEdge(N, minRoute);

        if(maxDiff == inf)
            System.out.println("-1");
        else System.out.println(maxDiff);

    }
    static void dijkstra(Node start){
        int count = 0;
        dist[start.num] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.dist - o2.dist;
            }
        });
        pq.add(start);
        while(!pq.isEmpty()){
            Node next = pq.poll();
            if(!visited[next.num]) {
                if(isFirst)
                    previous[next.num] = next.previous;
                count++;
            }
            visited[next.num] = true;
            if(count == N)
                break;
            for(Node adj : graph[next.num]) {
                if (!visited[adj.num] && dist[adj.num] > adj.dist + next.dist) {
                    pq.add(new Node(next.num, adj.num, adj.dist + next.dist));
                    dist[adj.num] = adj.dist + next.dist;
                }
            }
        }
    }

    static void deleteEdge(int n, int minRoute){
        if(n == 0)
            return;
        int adj = previous[n];
        Node temp1 = null, temp2 = null;
        for(Node ad : graph[n]) {
            if (ad.num == adj) {
                temp1 = ad;
                graph[n].remove(ad);
                break;
            }
        }
        for(Node ad : graph[adj])
            if(ad.num == n) {
                temp2 = ad;
                graph[adj].remove(ad);
                break;
            }
        for(int i = 0; i <= N; i++) {
            dist[i] = inf;
            visited[i] = false;
        }
        dijkstra(new Node(0, 1, 0));
        int otherRoute = dist[N];
        if(otherRoute == inf)
            maxDiff = inf;
        else
            maxDiff = Math.max(maxDiff, otherRoute - minRoute);
        graph[n].add(temp1);
        graph[adj].add(temp2);
        deleteEdge(previous[n], minRoute);
    }
}

class Node{
    int previous, num, dist;

    public Node(int previous, int num, int dist) {
        this.previous = previous;
        this.num = num;
        this.dist = dist;
    }
}
