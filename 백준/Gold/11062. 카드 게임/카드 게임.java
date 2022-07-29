import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, N;
    static int[] cards;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());

            cards = new int[N];
            dp = new int[N][N];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                cards[j] = Integer.parseInt(st.nextToken());
            System.out.println(solution(0, N - 1, 1));
        }
    }

    static int solution(int left, int right, int turn){
        if(left > right)
            return 0;
        if(dp[left][right] != 0)
            return dp[left][right];

        if(turn % 2 == 1)
            dp[left][right] = Math.max(solution(left + 1, right, turn + 1) + cards[left],
                    solution(left, right - 1, turn + 1) + cards[right]);
        else
            dp[left][right] = Math.min(solution(left + 1, right, turn + 1), solution(left, right - 1, turn + 1));
        return dp[left][right];
    }
}
