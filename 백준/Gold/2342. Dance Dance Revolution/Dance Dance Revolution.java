import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<Integer> list;
    static int[][] cost;
    static int[][][] dp;
    static int inf = 10000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        list = new ArrayList<>();
        cost = new int[5][5];

        while(true){
            int num = Integer.parseInt(st.nextToken());
            if(num == 0)
                break;
            else list.add(num);
        }
        dp = new int[5][5][list.size()];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                Arrays.fill(dp[i][j], -1);
                if(i == j)
                    cost[i][j] = 1;
                else if(i == 0)
                    cost[i][j] = 2;
                else if(Math.abs(i - j) == 2)
                    cost[i][j] = 4;
                else cost[i][j] = 3;
            }
        }
        System.out.println(solution(0, 0, 0));
    }
    static int solution(int x, int y, int order){
        int next = list.get(order);
        if(order == list.size() - 1)
            return Math.min(cost[x][next], cost[y][next]);

        if(dp[x][y][order] != -1)
            return dp[x][y][order];

        dp[x][y][order] = Math.min(cost[x][next] + solution(next, y, order + 1),
                cost[y][next] + solution(x, next, order + 1));
        return dp[x][y][order];
    }
}
