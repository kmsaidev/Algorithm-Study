import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        nums = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++)
            nums[i] = Integer.parseInt(st.nextToken());

        int low = 0, high = 0, sum = nums[0], min = 100001;
        while(true){
            if(sum >= M){
                int len = high - low + 1;
                min = Math.min(min, len);
                sum -= nums[low++];
            }
            else if(sum < M)
                sum += nums[++high];

            if(high == N)
                break;
        }

        if(min == 100001)
            System.out.println("0");
        else
            System.out.println(min);
    }
}
