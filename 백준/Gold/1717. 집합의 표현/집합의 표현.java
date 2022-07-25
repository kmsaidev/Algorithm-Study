import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K, a, b;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        initialize();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            if(K == 0){
                union(a, b);
            } else if(K == 1){
                if(isSameRoot(a, b))
                    System.out.println("YES");
                else System.out.println("NO");
            }
        }
    }

    static void initialize(){
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
        else
            return parent[a] = find(parent[a]);
    }

    static boolean isSameRoot(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return true;
        else return false;
    }
}
