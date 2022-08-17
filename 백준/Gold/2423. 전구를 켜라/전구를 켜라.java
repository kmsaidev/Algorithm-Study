import javax.swing.text.html.StyleSheet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Node>[][] graph;
    static int[][] dist;
    static int inf = 200000000;
    static int[] move_x = {1, -1, -1, 1};
    static int[] move_y = {1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 2][M + 2];
        dist = new int[N + 2][M + 2];

        for(int i = 0; i <= N + 1; i++)
            for (int j = 0; j <= M + 1; j++)
                graph[i][j] = new ArrayList<>();

        for(int i = 1; i <= N; i++) {
            String line = br.readLine();
            for(int j = 1; j <= M; j++){
                char c = line.charAt(j - 1);
                if(c == '/'){
                    int x = i;
                    int y = j + 1;
                    graph[x][y].add(new Node(x + 1, y - 1, 1));
                    graph[x + 1][y - 1].add(new Node(x, y, 1));
                    x = i;
                    y = j;
                    graph[x][y].add(new Node(x + 1, y + 1, 0));
                    graph[x + 1][y + 1].add(new Node(x, y, 0));
                }else if(c == '\\'){
                    int x = i;
                    int y = j;
                    graph[x][y].add(new Node(x + 1, y + 1, 1));
                    graph[x + 1][y + 1].add(new Node(x, y, 1));
                    x = i;
                    y = j + 1;
                    graph[x][y].add(new Node(x + 1, y - 1, 0));
                    graph[x + 1][y - 1].add(new Node(x, y, 0));
                }
            }
        }
        dijkstra(1, 1);
        if(dist[N + 1][M + 1] == inf)
            System.out.println("NO SOLUTION");
        else System.out.println(dist[N + 1][M + 1]);
    }

    static void dijkstra(int startX, int startY){
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(startX, startY, 0));
        for(int i = 0; i <= N + 1; i++)
            for (int j = 0; j <= M + 1; j++)
                dist[i][j] = inf;
        dist[startX][startY] = 0;
        while(!pq.isEmpty()){
            Node pop = pq.poll();
            if(dist[pop.x][pop.y] < pop.weight)
                continue;
            for(Node next : graph[pop.x][pop.y]){
                int weight = next.weight == 0 ? 1 : 0;
                if(dist[next.x][next.y] > dist[pop.x][pop.y] + weight){
                    dist[next.x][next.y] = dist[pop.x][pop.y] + weight;
                    pq.add(new Node(next.x, next.y, dist[next.x][next.y]));
                }
            }
        }
    }
}

class Node implements Comparable<Node>{
    int x, y, weight;

    public Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }

    public int compareTo(Node other){
        return this.weight - other.weight;
    }
}