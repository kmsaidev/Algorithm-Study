import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] bulbs;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bulbs = new int[N];
        dp = new int[N][N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            bulbs[i] = Integer.parseInt(st.nextToken());

        System.out.println(solution(0, N - 1));
    }

    static int solution(int s, int e){
        if(s == e)
            return 0;
        if(dp[s][e] != 0)
            return dp[s][e];
        int temp = Integer.MAX_VALUE;
        for(int i = s; i < e; i++){
            int leftResult = solution(s, i);
            int rightResult = solution(i + 1, e);

            if(bulbs[s] == bulbs[i + 1])
                temp = Math.min(temp, leftResult + rightResult);
            else
                temp = Math.min(temp, leftResult + rightResult + 1);
        }
        return dp[s][e] = temp;
    }
}
