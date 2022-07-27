import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M, Q;
    static int[] parent, count;
    static Edge[] edges;
    static ArrayDeque<Integer> remove;
    static boolean[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        count = new int[N + 1];
        init();
        edges = new Edge[M + 1];
        remove = new ArrayDeque<>();
        visited = new boolean[M + 1];
        long score = 0;

        for(int i = 1; i <= M; i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for(int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            remove.add(r);
            visited[r] = true;
        }
        for(int i = 1; i <= M; i++)
            if (!visited[i])
                union(edges[i].a, edges[i].b);

        while(!remove.isEmpty()){
            int num = remove.pollLast();
            int rootA = find(edges[num].a);
            int rootB = find(edges[num].b);
            if(rootA != rootB) {
                score += count[rootA] * count[rootB];
            }
            union(edges[num].a, edges[num].b);
        }

        System.out.println(score);
    }

    static void init(){
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
            count[i] = 1;
        }
    }

    static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB)
            return;
        parent[rootA] = rootB;
        count[rootB] += count[rootA];
        count[rootA] = 0;
    }

    static int find(int a){
        if(a == parent[a]) {
            return a;
        }
        else {
            return parent[a] = find(parent[a]);
        }
    }
}

class Edge{
    int a, b;

    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
}
