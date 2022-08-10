import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] inDegree, cost, time;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        inDegree = new int[N + 1];
        cost = new int[N + 1];
        time = new int[N + 1];
        graph = new ArrayList[N + 1];
        for(int i = 0; i <= N; i++)
            graph[i] = new ArrayList<>();
        for(int i = 1; i <= N; i++){
            st = new StringTokenizer(br.readLine());
            cost[i] = Integer.parseInt(st.nextToken());
            int before = Integer.parseInt(st.nextToken());
            while(before != -1){
                graph[before].add(i);
                inDegree[i]++;
                before = Integer.parseInt(st.nextToken());
            }
        }

        topologicalSort();
        for(int i = 1; i <= N; i++)
            System.out.println(time[i]);
    }

    static void topologicalSort(){
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= N; i++) {
            if (inDegree[i] == 0)
                queue.add(i);
            time[i] = cost[i];
        }

        while(!queue.isEmpty()){
            int next = queue.poll();
            for(int node : graph[next]){
                inDegree[node]--;
                if(inDegree[node] == 0) {
                    queue.add(node);
                }
                time[node] = Math.max(time[node], time[next] + cost[node]);
            }
        }
    }
}
