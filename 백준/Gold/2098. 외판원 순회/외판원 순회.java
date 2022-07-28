import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] cost;
    static int[][] dp;
    static int inf = 1000000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        cost = new int[N][N];
        dp = new int[N][(1 << N) - 1];


        for(int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                cost[i][j] = Integer.parseInt(st.nextToken());
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp(int city, int visited){
        if(visited == (1 << N) - 1){
            if(cost[city][0] == 0)
                return inf;
            return cost[city][0];
        }

        if(dp[city][visited] != -1)
            return dp[city][visited];

        dp[city][visited] = inf;
        for(int i = 0; i < N; i++)
            if((visited & (1 << i)) == 0 && cost[city][i] != 0)
                dp[city][visited] = Math.min(dp[city][visited], tsp(i, visited | (1 << i)) + cost[city][i]);
        return dp[city][visited];
    }
}
