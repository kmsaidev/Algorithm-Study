import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, Q;
    static long K;
    static long[] fact;
    static int[] permutation;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        fact = new long[21];
        visited = new boolean[N + 1];
        permutation = new int[N];

        fact[0] = 1;
        for (int i = 1; i <= 20; ++i) {
            fact[i] = fact[i - 1] * i;
        }

        st = new StringTokenizer(br.readLine());
        Q = Integer.parseInt(st.nextToken());
        if(Q == 1) {
            K = Long.parseLong(st.nextToken());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i <= N; i++){
                for(int j = 1; j <= N; j++){
                    if(visited[j])
                        continue;
                    if(K > fact[N - i - 1]){
                        K -= fact[N - i - 1];
                    } else{
                        sb.append(j);
                        sb.append(" ");
                        visited[j] = true;
                        break;
                    }
                }
            }
            System.out.println(sb.toString());
        } else if(Q == 2){
            for(int i = 0; i < N; i++)
                permutation[i] = Integer.parseInt(st.nextToken());

            long result = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 1; j < permutation[i]; j++) {
                    if (visited[j] == false) {
                        result += fact[N - i - 1];
                    }
                }
                visited[permutation[i]] = true;
            }
            System.out.println(result + 1);
        }
    }

}
