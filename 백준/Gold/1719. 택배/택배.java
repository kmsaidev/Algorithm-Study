import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.NotDirectoryException;
import java.util.*;

public class Main {
    static int N, M, A, B, W;
    static ArrayList<Node>[] graph;
    static int[] dist;
    static int inf = Integer.MAX_VALUE;
    static int[][] result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        dist = new int[N + 1];
        result = new int[N + 1][N + 1];
        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            graph[A].add(new Node(B, W));
            graph[B].add(new Node(A, W));
        }

        for(int i = 1; i <= N; i++){
            dijkstra(i);
            for(int j = 1; j <= N; j++){
                if(result[i][j] != j){
                    result[i][j] = getFirstNode(i, result[i][j]);
                }
            }
        }

        for(int i = 1; i <= N; i++){
            for(int j = 1; j <= N; j++){
                if(i != j)
                    System.out.print(result[i][j] + " ");
                else System.out.print("- ");
            }
            System.out.println();
        }
    }
    static void dijkstra(int start){
        Arrays.fill(dist, inf);
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
                    if(pop.num == start)
                        result[start][next.num] = next.num;
                    else
                        result[start][next.num] = pop.num;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
    }

    static int getFirstNode(int start, int n){
        if(result[start][n] == n)
            return n;
        return getFirstNode(start, result[start][n]);
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
