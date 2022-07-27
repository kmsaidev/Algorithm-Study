import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int N, M, A, B, W;
    static int[] parent;
    static int[] sum;
    static char type;
    static int max = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0)
                break;

            parent = new int[N + 1];
            sum = new int[N + 1];
            init();

            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                type = st.nextToken().charAt(0);
                A = Integer.parseInt(st.nextToken());
                B = Integer.parseInt(st.nextToken());
                if (type == '!') {
                    W = Integer.parseInt(st.nextToken());
                    union(A, B, W);
                } else if (type == '?') {
                    int answer = query(A, B);
                    if (answer == max)
                        System.out.println("UNKNOWN");
                    else
                        System.out.println(answer);
                }
            }
        }
    }

    static void init(){
        for(int i = 1; i <= N; i++)
            parent[i] = i;
    }

    static void union(int a, int b, int w){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB)
            return;
        parent[rootA] = rootB;
        sum[rootA] = sum[b] - sum[a] + w;
    }

    static int find(int a){
        if(parent[a] == a) {
            return a;
        }
        int p = find(parent[a]);
        sum[a] += sum[parent[a]];
        parent[a] = p;
        return p;
    }

    static int query(int a, int b) {
        if(find(a) != find(b))
            return max;
        return sum[a] - sum[b];
    }
}

