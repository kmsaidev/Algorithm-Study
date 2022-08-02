import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Edge> edges;
    static long[] dist;
    static long inf = Long.MAX_VALUE;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        edges = new ArrayList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new long[N + 1];

        for(int i = 1; i <= N; i++)
            dist[i] = inf;

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken())));
        }

        int start = 1;
        dist[start] = 0;
        boolean isCycle = false;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                Edge edge = edges.get(j);
                if(dist[edge.start] != inf && dist[edge.start] + edge.weight < dist[edge.end]){
                    dist[edge.end] = dist[edge.start] + edge.weight;
                    if(i == N - 1)
                        isCycle = true;
                }
            }
        }
        if(isCycle)
            System.out.println("-1");
        else{
            for(int i = 2; i <= N; i++){
                if(dist[i] == inf)
                    System.out.println("-1");
                else System.out.println(dist[i]);
            }
        }
    }
}

class Edge{
    int start, end, weight;

    public Edge(int start, int end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
