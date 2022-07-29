import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, min;
    static int[] memory;
    static int[] cost;
    static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        memory = new int[N + 1];
        cost = new int[N + 1];
        int totalCost = 0;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            memory[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            cost[i] = Integer.parseInt(st.nextToken());
            totalCost += cost[i];
        }
        dp = new int[totalCost + 1];

        for(int i = 1; i <= N; i++) {
            for (int j = totalCost; j >= 0; j--) {
                if(j >= cost[i])
                    dp[j] = Math.max(dp[j - cost[i]] + memory[i], dp[j]);
            }
        }
        for(int i = 0; i <= totalCost; i++) {
            if (dp[i] >= M){
                min = i;
                break;
            }
        }
        System.out.println(min);
    }
}
