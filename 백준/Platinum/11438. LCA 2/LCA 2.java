import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, A, B;
    static ArrayList<Integer>[] tree;
    static int[][] dp;
    static int[] depth;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        depth = new int[N + 1];
        dp = new int[18][N + 1];
        for(int i = 0; i <= N; i++)
            tree[i] = new ArrayList<>();


        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            tree[A].add(B);
            tree[B].add(A);
        }
        init(new Node(1, 1));

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            System.out.println(lca());
        }
    }

    static void init(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        visited[root.num] = true;
        while(!queue.isEmpty()){
            Node item = queue.poll();
            depth[item.num] = item.depth;
            for(int t : tree[item.num]) {
                if(!visited[t]) {
                    queue.add(new Node(t, item.depth + 1));
                    dp[0][t] = item.num;
                    visited[t] = true;
                }
            }
        }
        for(int i = 1; i <= 17; i++)
            for (int j = 1; j <= N; j++)
                dp[i][j] = dp[i - 1][dp[i - 1][j]];
    }
    static int lca(){
        int depthA = depth[A];
        int depthB = depth[B];
        if(depthA != depthB) {
            int diff = depthA - depthB;
            if (diff < 0) {
                int temp = A;
                A = B;
                B = temp;
                diff = -diff;
            }

            for(int i = 17; i >= 0; i--)
                if((diff & (1 << i)) > 0)
                    A = dp[i][A];
        }
        if(A == B)
            return A;
        for(int i = 17; i >= 0; i--){
            if(dp[i][A] != dp[i][B]) {
                A = dp[i][A];
                B = dp[i][B];
            }
        }
        return dp[0][A];
    }
}

class Node{
    int num;
    int depth;

    public Node(int num, int depth) {
        this.num = num;
        this.depth = depth;
    }
}
