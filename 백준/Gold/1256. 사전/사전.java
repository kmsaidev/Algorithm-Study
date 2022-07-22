import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] dp;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + M + 1][N + M + 1];

        for(int i = 0; i < dp.length; i++)
            dp[i][0] = 1;
        for(int i = 0; i < dp.length; i++)
            dp[i][i] = 1;

        for(int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[i].length; j++) {
                if (i != j && j != 0)
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                if(dp[i][j] > K)
                    dp[i][j] = K;
            }
        }
        sb = new StringBuilder();
        query(N + M, M, K);

        String ans = sb.toString();
        if(ans.length() == 0)
            System.out.println("-1");
        else
            System.out.println(ans);
    }
    static void query(int n, int pick, int target){
        if(dp[n][pick] < target)
            return;
        if(n - 1 < 0)
            return;
        if (dp[n - 1][pick] >= target) {
            sb.append('a');
            query(n - 1, pick, target);
        } else if (dp[n - 1][pick] < target) {
            sb.append('z');
            query(n - 1, pick - 1, target - dp[n - 1][pick]);
        }
    }
}
