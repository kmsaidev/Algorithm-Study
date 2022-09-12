import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N, max;
    static long[] dp, sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        max = 1000000;
        dp = new long[max + 1];
        sum = new long[max + 1];
        T = Integer.parseInt(st.nextToken());

        for(int i = 1; i <= max; i++){
            for(int j = 1; i * j <= max; j++)
                dp[i * j] += i;
        }
        sum[1] = 1;
        for(int i = 2; i <= max; i++)
            sum[i] = sum[i - 1] + dp[i];
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            sb.append(sum[N] + "\n");
        }
        System.out.println(sb);
    }
}
