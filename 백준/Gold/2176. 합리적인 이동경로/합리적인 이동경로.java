import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, A, B, C;
    static ArrayList<Node>[] graph;
    static int[] dist, dp;
    static int inf = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        dp = new int[N + 1];
        dp[2] = 1;
        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();
        Arrays.fill(dist, inf);
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, C));
            graph[B].add(new Node(A, C));
        }
        dijkstra(2);
        System.out.println(solution(1));
    }
    static void dijkstra(int start){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node pop = pq.poll();
            if(pop.weight > dist[pop.num])
                continue;
            for(Node next : graph[pop.num]){
                if(dist[next.num] > dist[pop.num] + next.weight){
                    dist[next.num] = dist[pop.num] + next.weight;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
    }

    static int solution(int start){
        if(dp[start] == 0) {
            for (Node n : graph[start])
                if (dist[n.num] < dist[start])
                    dp[start] += solution(n.num);
            return dp[start];
        }
        else return dp[start];
    }
}

class Node implements Comparable<Node>{
    int num, weight;

    public Node(int num, int weight) {
        this.num = num;
        this.weight = weight;
    }

    public int compareTo(Node other){
        return this.weight - other.weight;
    }
}
