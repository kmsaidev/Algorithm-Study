import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static Edge[] edges;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int minPrice = 0;
        int edgeCount = 0;
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        init();

        edges = new Edge[M];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            edges[i] = new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });

        for(Edge edge : edges){
            if(edgeCount == N)
                break;
            if(!isSameRoot(edge.a, edge.b)){
                union(edge.a, edge.b);
                minPrice += edge.weight;
            }
        }

        System.out.println(minPrice);
    }

    static void init(){
        for(int i = 1; i <= N; i++)
            parent[i] = i;
    }

    static void union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        parent[aRoot] = bRoot;
    }

    static int find(int a){
        if(parent[a] == a)
            return a;
        else return parent[a] = find(parent[a]);
    }
    static boolean isSameRoot(int a, int b){
        return (find(a) == find(b));
    }
}

class Edge{
    int a, b;
    int weight;

    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }
}
