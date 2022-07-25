import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int R, U, V;
    static ArrayList<Integer>[] tree;
    static int[] depth;
    static boolean[] visited;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++)
            tree[i] = new ArrayList<>();


        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());

            tree[U].add(V);
            tree[V].add(U);
        }

        dp = new int[18][N + 1];
        depth = new int[N + 1];
        visited = new boolean[N + 1];
        init(new Node(1, 1));
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken());
            U = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            int result = lca(R, U);
            int temp = lca(U, V);
            result = depth[result] >= depth[temp] ? result : temp;
            temp = lca(R, V);
            result = depth[result] >= depth[temp] ? result : temp;
            System.out.println(result);
        }
    }

    static void init(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        visited[root.num] = true;

        while(!queue.isEmpty()){
            Node item = queue.poll();
            depth[item.num] = item.depth;
            for(int node : tree[item.num]){
                if(!visited[node]){
                    queue.add(new Node(node, item.depth + 1));
                    visited[node] = true;
                    dp[0][node] = item.num;
                }
            }
        }

        for(int i = 1; i <= 17; i++)
            for(int j = 1; j <= N; j++)
                dp[i][j] = dp[i - 1][dp[i - 1][j]];
    }

    static int lca(int a, int b){
        int depthA = depth[a];
        int depthB = depth[b];
        if(depthA != depthB){
            int diff = depthA - depthB;
            if(diff < 0){
                int temp = a;
                a = b;
                b = temp;
                diff = -diff;
            }
            for(int i = 17; i >= 0; i--)
                if((diff & (1 << i)) > 0)
                    a = dp[i][a];
        }
        if(a == b)
            return a;
        for(int i = 17; i >= 0; i--){
            if(dp[i][a] != dp[i][b]){
                a = dp[i][a];
                b = dp[i][b];
            }
        }
        return dp[0][a];
    }
}

class Node{
    int num, depth;

    public Node(int num, int depth) {
        this.num = num;
        this.depth = depth;
    }
}
