import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] triangle;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        triangle = new int[N][N];
        dp = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j <= i; j++)
                triangle[i][j] = Integer.parseInt(st.nextToken());
        }

        for(int i = 0; i < N; i++)
            dp[N - 1][i] = triangle[N - 1][i];

        for(int i = N - 2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];

        System.out.println(dp[0][0]);
    }
}
