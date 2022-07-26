import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M, order;
    static int[] inOrder, outOrder;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        inOrder = new int[N + 1];
        outOrder = new int[N + 1];

        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
        }
        for(int i = 1; i <= N; i++){
            order = 0;
            visited = new boolean[N + 1];
            outOrder[i] = dfs(i) - 1;
        }

        for(int i = 1; i <= N; i++)
            if(inOrder[i] + outOrder[i] == N - 1)
                result++;
        System.out.println(result);
    }

    static int dfs(int start){
        int outCnt = 0;
        for(int adj : graph[start]) {
            if (!visited[adj]) {
                inOrder[adj]++;
                visited[adj] = true;
                outCnt += dfs(adj);
            }
        }
        return outCnt + 1;
    }
}

