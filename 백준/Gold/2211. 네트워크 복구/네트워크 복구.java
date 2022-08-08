import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int N, M, A, B, W;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static boolean[] visited;
    static int inf = Integer.MAX_VALUE;
    static HashSet<Edge> set;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        set = new HashSet<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();
        Arrays.fill(dist, inf);
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, W));
            graph[B].add(new Node(A, W));
        }
        dijkstra(new Node(1, 0));
        findRoute();
        System.out.println(set.size());
        for(Edge e : set){
            System.out.println(e.a + " " + e.b);
        }
    }

    static void dijkstra(Node start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(start);

        dist[start.num] = 0;
        while(!pq.isEmpty()){
            Node pop = pq.poll();
            if(!visited[pop.num])
                visited[pop.num] = true;
            for(Node next : graph[pop.num]){
                if(!visited[next.num] && dist[next.num] > dist[pop.num] + next.weight){
                    dist[next.num] = dist[pop.num] + next.weight;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
    }

    static void findRoute(){
        for(int i = 2; i <= N; i++){
            int distance = dist[i];
            find(i, distance);
        }
    }

    static void find(int nodeNum, int distance){
        if(nodeNum == 1)
            return;
        for(Node next : graph[nodeNum]){
            if(distance == next.weight + dist[next.num]){
                if(!set.contains(new Edge(nodeNum, next.num)) && !set.contains(new Edge(next.num, nodeNum)))
                    set.add(new Edge(nodeNum, next.num));
                find(next.num, dist[next.num]);
                break;
            }
        }
    }
}

class Node implements Comparable<Node> {
    int num, weight;

    public Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }

    public int compareTo(Node other){
        return this.weight - other.weight;
    }
}

class Edge {
    int a, b;

    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return a == edge.a && b == edge.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
