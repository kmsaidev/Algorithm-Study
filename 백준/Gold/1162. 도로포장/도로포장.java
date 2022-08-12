import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, A, B, W;
    static ArrayList<Node>[] graph;
    static long[][] dist;
    static boolean[] visited;
    static long inf = Long.MAX_VALUE;
    static long minDist = Long.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new long[N + 1][K + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<>();
            Arrays.fill(dist[i], inf);
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, W, 0));
            graph[B].add(new Node(A, W, 0));
        }

        dijkstra();
        for(int i = 0; i <= K; i++)
            minDist = Math.min(minDist, dist[N][i]);

        System.out.println(minDist);
    }
    static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));
        dist[1][0] = 0;

        while(!pq.isEmpty()){
            Node pop = pq.poll();
            if(dist[pop.num][pop.empty] < pop.weight)
                continue;
            for(Node next : graph[pop.num]){
                if(dist[next.num][pop.empty] > dist[pop.num][pop.empty] + next.weight){
                    dist[next.num][pop.empty] = dist[pop.num][pop.empty] + next.weight;
                    pq.add(new Node(next.num, dist[next.num][pop.empty], pop.empty));
                }
                if(pop.empty + 1 <= K && dist[next.num][pop.empty + 1] > dist[pop.num][pop.empty]){
                    dist[next.num][pop.empty + 1] = dist[pop.num][pop.empty];
                    pq.add(new Node(next.num, dist[next.num][pop.empty + 1], pop.empty + 1));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int num;
    long weight;
    int empty;

    public Node(int num, long weight, int empty) {
        this.num = num;
        this.weight = weight;
        this.empty = empty;
    }

    public int compareTo(Node other){
        return Long.compare(this.weight, other.weight);
    }
}
