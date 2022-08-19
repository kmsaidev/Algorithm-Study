import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static ArrayList<Integer>[] graph;
    static int[] depth;
    static boolean[] visited;
    static int[][] sTable, dTable;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        depth = new int[N + 1];
        visited = new boolean[N + 1];
        sTable = new int[16][N + 1];
        dTable = new int[16][N + 1];

        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();
        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            graph[b].add(a);
        }
        init();
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        int start = 1;
        int result = 0;
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int target = Integer.parseInt(st.nextToken());
            result += lca(start, target);
            start = target;
        }
        System.out.println(result);
    }

    static void init(){
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(1, 0));

        while(!queue.isEmpty()){
            Node pop = queue.poll();
            depth[pop.num] = pop.depth;
            visited[pop.num] = true;
            for(int next : graph[pop.num]){
                if(!visited[next]){
                    queue.add(new Node(next, pop.depth + 1));
                    sTable[0][next] = pop.num;
                    dTable[0][next] = 1;
                }
            }
        }

        for(int i = 1; i < 16; i++) {
            for (int j = 1; j <= N; j++){
                sTable[i][j] = sTable[i - 1][sTable[i - 1][j]];
                dTable[i][j] = dTable[i - 1][j] + dTable[i - 1][sTable[i - 1][j]];
            }
        }
    }

    static int lca(int a, int b){
        int depthA = depth[a];
        int depthB = depth[b];
        int diff = depthA - depthB;
        int result = 0;
        if(diff != 0){
            if(diff < 0){
                int temp = a;
                a = b;
                b = temp;
                diff = -1 * diff;
            }
            for(int i = 15; i >= 0; i--){
                if((diff & (1 << i)) > 0) {
                    result += dTable[i][a];
                    a = sTable[i][a];
                }
            }
        }
        if(a == b)
            return result;

        for(int i = 15; i >= 0; i--){
            if(sTable[i][a] != sTable[i][b]){
                result += dTable[i][a] + dTable[i][b];
                a = sTable[i][a];
                b = sTable[i][b];
            }
        }
        result += dTable[0][a] + dTable[0][b];
        return result;
    }
}

class Node{
    int num, depth;

    public Node(int num, int depth) {
        this.num = num;
        this.depth = depth;
    }
}
