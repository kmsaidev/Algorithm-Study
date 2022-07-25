import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, A, B;
    static ArrayList<Integer>[] graph;
    static int[] size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        Queue<Integer> queue = new LinkedList<>();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N + 1];
        size = new int[N + 1];
        for(int i = 1; i <= N; i++)
            graph[i] = new ArrayList<>();

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());

            graph[A].add(B);
            size[B]++;
        }

        for(int i = 1; i <= N; i++)
            if(size[i] == 0)
                queue.add(i);

        while(!queue.isEmpty()){
            int person = queue.poll();
            System.out.print(person + " ");
            for(int i = 0; i < graph[person].size(); i++){
                int adjP = graph[person].get(i);
                size[adjP]--;
                if(size[adjP] == 0)
                    queue.add(adjP);
            }
        }
    }
}
