import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int W, H, G, E;
    static int X1, Y1, X2, Y2, T;
    static long[][] dist;
    static boolean[][] isBlock, visited;
    static Node[][] holes;
    static long inf = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        dist = new long[30][30];
        isBlock = new boolean[30][30];
        holes = new Node[30][30];
        visited = new boolean[30][30];
        while(true){
            st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            if(W == 0 && H == 0)
                break;
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    dist[i][j] = inf;
                    isBlock[i][j] = false;
                    holes[i][j] = null;
                    visited[i][j] = false;
                }
            }
            st = new StringTokenizer(br.readLine());
            G = Integer.parseInt(st.nextToken());
            for(int i = 0; i < G; i++){
                st = new StringTokenizer(br.readLine());
                X1 = Integer.parseInt(st.nextToken());
                Y1 = Integer.parseInt(st.nextToken());
                isBlock[Y1][X1] = true;
            }
            st = new StringTokenizer(br.readLine());
            E = Integer.parseInt(st.nextToken());
            for(int i = 0; i < E; i++){
                st = new StringTokenizer(br.readLine());
                X1 = Integer.parseInt(st.nextToken());
                Y1 = Integer.parseInt(st.nextToken());
                X2 = Integer.parseInt(st.nextToken());
                Y2 = Integer.parseInt(st.nextToken());
                T = Integer.parseInt(st.nextToken());
                holes[Y1][X1] = new Node(X2, Y2, T);
            }

            boolean isNotCycle = bellmanFord(new Node(0, 0, 0));
            if(!isNotCycle){
                System.out.println("Never");
            } else{
                if(dist[H - 1][W - 1] == inf)
                    System.out.println("Impossible");
                else System.out.println(dist[H - 1][W - 1]);
            }
        }
    }
    static boolean bellmanFord(Node start){
        dist[start.y][start.x] = 0;
        int[] move_x = {0, 0, -1, 1};
        int[] move_y = {-1, 1, 0, 0};

        for(int i = 0; i < H * W; i++){
            for(int j = 0; j < H; j++){
                for(int k = 0; k < W; k++){
                    if(isBlock[j][k] || (j == H - 1 && k == W - 1))
                        continue;
                    if(holes[j][k] != null){
                        int new_y = holes[j][k].y;
                        int new_x = holes[j][k].x;
                        int w = holes[j][k].weight;
                        if(dist[j][k] != inf && dist[j][k] + w < dist[new_y][new_x]){
                            dist[new_y][new_x] = dist[j][k] + w;
                            if(i == H * W - 1)
                                return false;
                        }
                    } else{
                        for(int p = 0; p < 4; p++) {
                            int new_y = j + move_y[p];
                            int new_x = k + move_x[p];
                            if (0 <= new_x && new_x < W && 0 <= new_y && new_y < H) {
                                if(dist[j][k] != inf && dist[j][k] + 1 < dist[new_y][new_x]){
                                    dist[new_y][new_x] = dist[j][k] + 1;
                                    if(i == H * W - 1)
                                        return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}

class Node{
    int x, y, weight;

    public Node(int x, int y, int weight) {
        this.x = x;
        this.y = y;
        this.weight = weight;
    }
}

class Edge{
    Node nodeS, nodeE;
    int weight;

    public Edge(Node nodeS, Node nodeE, int weight) {
        this.nodeS = nodeS;
        this.nodeE = nodeE;
        this.weight = weight;
    }
}
