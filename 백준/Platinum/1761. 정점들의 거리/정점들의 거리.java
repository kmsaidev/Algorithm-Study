import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, A, B, W, answer;
    static int[] depth;
    static int[][] sTable, wTable;
    static ArrayList<Node>[] tree;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        depth = new int[N + 1];
        sTable = new int[17][N + 1];
        wTable = new int[17][N + 1];
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        for(int i = 0; i <= N; i++)
            tree[i] = new ArrayList<>();

        for(int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            tree[A].add(new Node(B, W));
            tree[B].add(new Node(A, W));
        }
        init(new Node(1, 1));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            answer = 0;
            lca(A, B);
            System.out.println(answer);
        }
    }

    static void init(Node start){
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        sTable[0][start.num] = start.num;

        while(!queue.isEmpty()){
            Node next = queue.poll();
            visited[next.num] = true;
            depth[next.num] = next.depth;
            for(Node adj : tree[next.num]){
                if(!visited[adj.num]){
                    queue.add(new Node(adj.num, next.depth + 1));
                    sTable[0][adj.num] = next.num;
                    wTable[0][adj.num] = adj.depth;
                }
            }
        }

        for(int i = 1; i < 17; i++){
            for(int j = 1; j <= N; j++){
                sTable[i][j] = sTable[i - 1][sTable[i - 1][j]];
                wTable[i][j] += wTable[i - 1][j] + wTable[i - 1][sTable[i - 1][j]];
            }
        }
    }

    static void lca(int a, int b){
        int depthA = depth[a];
        int depthB = depth[b];
        if(depthA != depthB){
            int diff = depthA - depthB;
            if(diff < 0){
                int temp = a;
                a = b;
                b = temp;
                depthA = depth[a];
                depthB = depth[b];
                diff = depthA - depthB;
            }
            for(int i = 16; i >= 0; i--) {
                if ((diff & (1 << i)) > 0) {
                    answer += wTable[i][a];
                    a = sTable[i][a];
                }
            }
        }
        if(a == b)
            return;

        for(int i = 16; i >= 0; i--){
            if(sTable[i][a] != sTable[i][b]){
                answer += wTable[i][a] + wTable[i][b];
                a = sTable[i][a];
                b = sTable[i][b];
            }
        }
        answer += wTable[0][a] + wTable[0][b];
    }
}

class Node{
    int num, depth;

    public Node(int num, int depth) {
        this.num = num;
        this.depth = depth;
    }
}
