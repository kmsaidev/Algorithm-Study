import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int K, V, E;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] colors;
    static ArrayList<Integer> starts;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        graph = new ArrayList[20001];
        visited = new boolean[20001];
        colors = new int[20001];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            starts = new ArrayList<>();
            for(int j = 1; j <= V; j++){
                visited[j] = false;
                colors[j] = 0;
            }
            for(int j = 0; j <= V; j++)
                graph[j] = new ArrayList<>();
            for(int j = 0; j < E; j++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }


            if(bfs())
                System.out.println("YES");
            else System.out.println("NO");
        }
    }
    static boolean bfs(){
        Queue<Integer> queue = new LinkedList<>();

        for(int i = 1; i <= V; i++) {
            if(!visited[i]) {
                queue.add(i);
                colors[i] = 1;
            }
            while (!queue.isEmpty()) {
                int next = queue.poll();
                visited[next] = true;
                for (int node : graph[next]) {
                    if (colors[node] == colors[next])
                        return false;
                    if (!visited[node]) {
                        colors[node] = colors[next] == 1 ? 2 : 1;
                        queue.add(node);
                    }
                }
            }
        }
        return true;
    }
}

