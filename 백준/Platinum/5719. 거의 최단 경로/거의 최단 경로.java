import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static int S, D, U, V, P;
    static int[][] graph;
    static int[] dist;
    static int inf = 100000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        dist = new int[500];
        graph = new int[500][500];
        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            if(N == 0 && M == 0)
                break;
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    graph[i][j] = 0;
            st = new StringTokenizer(br.readLine());

            S = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());
            for (int i = 0; i < M; i++){
                st = new StringTokenizer(br.readLine());
                U = Integer.parseInt(st.nextToken());
                V = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());

                graph[U][V] = P;
            }
            dijkstra(S);
//            for(int i = 0; i < N; i++)
//                System.out.print(dist[i]);
//            System.out.println();
            bfs(D);
            dijkstra(S);
            if(dist[D] == inf)
                System.out.println("-1");
            else
                System.out.println(dist[D]);
        }
    }
    static void dijkstra(int s){
        boolean[] visited = new boolean[N];
        for(int i = 0; i < N; i++)
            dist[i] = inf;
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.distance - o2.distance;
            }
        });
        pq.add(new Node(s, 0));
        dist[s] = 0;

        while(!pq.isEmpty()){
            Node pop = pq.poll();
            if(!visited[pop.num]) {
                visited[pop.num] = true;
                for (int i = 0; i < N; i++) {
                    if (graph[pop.num][i] != 0 ) {
                        int temp = pop.distance + graph[pop.num][i];
                        if (dist[i] > temp) {
                            dist[i] = temp;
                            pq.add(new Node(i, temp));
                        } else pq.add(new Node(i, dist[i]));

                    }
                }
            }
        }
    }

    static void bfs(int s){
        boolean[] visited = new boolean[N];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);

        while(!queue.isEmpty()){
            int n = queue.poll();
            if(!visited[n]) {
                visited[n] = true;
                for (int i = 0; i < N; i++) {
                    if (graph[i][n] != 0 && dist[n] - graph[i][n] == dist[i]) {
                        queue.add(i);
                        graph[i][n] = 0;
                    }
                }
            }
        }
    }
}

class Node{
    int num, distance;

    public Node(int num, int distance) {
        this.num = num;
        this.distance = distance;
    }
}
