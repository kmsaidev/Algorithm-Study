import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int V, E;
    static ArrayList<Integer>[] graph;
    static int[] visited;
    static boolean[] result;
    static ArrayList<Integer> answer;
    static int count = 1, resultCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[V + 1];
        visited = new int[V + 1];
        result = new boolean[V + 1];
        answer = new ArrayList<>();
        for(int i = 0; i <= V; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < E; i++){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            graph[B].add(A);
        }

        for(int i = 1; i <= V; i++)
            if(visited[i] == 0)
                dfs(i, true);


        for(int i = 1; i <= V; i++) {
            if (result[i]) {
                resultCount++;
                answer.add(i);
            }
        }
        System.out.println(resultCount);
        for(int ans : answer)
            System.out.print(ans + " ");
    }

    static int dfs(int start, boolean isRoot){
        visited[start] = count++;
        int temp = visited[start];

        int childCount = 0;
        for(int node : graph[start]){
            if(visited[node] == 0){
                childCount++;
                int low = dfs(node, false);
                temp = Math.min(temp, low);
                if(!isRoot && low >= visited[start])
                    result[start] = true;
            } else{
                temp = Math.min(temp, visited[node]);
            }
        }
        if(isRoot && childCount >= 2)
            result[start] = true;
        return temp;
    }
}
